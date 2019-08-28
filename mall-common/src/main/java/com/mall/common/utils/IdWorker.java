package com.mall.common.utils;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * <p>名称：IdWorker.java</p>
 * <p>描述：分布式自增长ID</p>
 * <pre>
 *     Twitter的 Snowflake　JAVA实现方案
 * </pre>
 * 核心代码为其IdWorker这个类实现，其原理结构如下，我分别用一个0表示一位，用—分割开部分的作用：
 * 1||0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000
 * 在上面的字符串中，第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间，
 * 然后5位datacenter标识位，5位机器ID（并不算标识符，实际是为线程标识），
 * 然后12位该毫秒内的当前毫秒内的计数，加起来刚好64位，为一个Long型。
 * 这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和机器ID作区分），
 * 并且效率较高，经测试，snowflake每秒能够产生26万ID左右，完全满足需要。
 * <p>
 * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
 *
 * @author wangyongchun
 */
@Slf4j
public class IdWorker {

    //时间标记起始点,作为基准，一般取系统的最近时间(1288834974657L一旦确定不能变动 1520855475726)
    private final static long twepoch = 1288834974657L;
    //机器标识位数
    private static final long workIdBits = 5L;
    //数据中心标识位数
    private static final long datacenterIdBits = 5L;
    //机器ID最大值
    private static final long maxWorkerId = -1L ^ (-1 << workIdBits);
    //数据中心ID最大值
    private static final long maxDatacenterId = -1L ^ (-1 << datacenterIdBits);
    //毫秒内自增位
    private static final long sequenceBits = 12L;
    //机器ID偏左移12位
    private static final long workerIdShift = sequenceBits;
    //数据中心ID左移17位
    private static final long datacenterIdShift = sequenceBits + workIdBits;
    //时间毫秒左移22位
    private static final long timestampLeftShift = sequenceBits + workIdBits + datacenterIdBits;

    private static final long sequenceMark = -1L ^ (-1L << sequenceBits);
    //上次生产id时间戳
    private static long lastTimeStamp = -1L;
    //并发控制
    private long sequence = 0L;

    private final long workerId;
    //数据标识id部分
    private final long datacenterId;

    public IdWorker(){
        this.datacenterId = getDatacenterId(maxDatacenterId);
        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
    }

    /*
    * @param workerId 工作机器ID
    * @param datacenterId 序列号
    */
    public IdWorker(long workerId, long datacenterId){
        if (workerId > maxWorkerId || workerId < 0){
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0){
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /*
    * 获取下一个ID
    *
    * @return
    */
    public synchronized long nextId(){
        long timestamp = timeGen();
        if (timestamp < lastTimeStamp){
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimeStamp - timestamp));
        }

        if (timestamp == lastTimeStamp){
            //当前毫秒内则加1
            sequence = (sequence + 1) & sequenceMark;
            if (sequence == 0){
                //当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMills(lastTimeStamp);
            }
        }else {
            sequence = 0L;
        }
        lastTimeStamp = timestamp;
        //偏移组合生成最终的id，并且返回id
        long nextId = ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId | workerIdShift) | sequence;

        return nextId;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }

    private long tilNextMills(final long lastTimeStamp){
        long timestamp = this.timeGen();
        while (timestamp <= lastTimeStamp){
            timestamp = this.timeGen();
        }

        return timestamp;
    }

    /*
    * <p>
    * 数据标识id部分
    * </p>
    */
    public static long getDatacenterId(long maxDatacenterId){
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null){
                id = 1L;
            }else {
                byte[] mac = network.getHardwareAddress();
                if (mac == null){
                    id = 1L;
                }else {
                    id = ((0x000000FF & (long) mac[mac.length - 1])
                            | (0x000000FF & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("getDatacenterId: " + e.getMessage());
        }

        return id;
    }

    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()){
            //Get JVMPID
            stringBuffer.append(name.split("@")[0]);
        }

        // MAC + PID的hashcode获取16位
        return (stringBuffer.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    public static void main(String[] args){
        IdWorker idWorker = new IdWorker(10, 5);
        long orderId = idWorker.nextId();
        System.out.println("idWorker = " + idWorker.nextId());
        long maxDbCount = -1L ^ (-1L << 5);
        System.out.println("maxDbCount=>>maxDbCount:===="+maxDbCount);
        int db = (int) ((orderId >> 12) & maxDbCount);
        System.out.println("workerId=>>12:===="+db);
        int datacenterId = (int) ((orderId >> 17) & maxDbCount);
        System.out.println("datacenterId=>>17:===="+datacenterId);
        long timeMaxVal = -1L ^ (-1L << 42);
        long timestamp = ((orderId >> 22) & timeMaxVal);
        System.out.println("timestamp=>>22:====timestamp - twepochtimestamp - twepoch:"+timestamp);
        long sequenceMask = -1L ^ (-1L << sequenceBits);
        System.out.println("sequenceMask=:===="+sequenceMask);
        System.out.println("idWorker="+idWorker.nextId());
        IdWorker id = new IdWorker();
        System.out.println("id = " + id.nextId());
        System.out.println(id.datacenterId);
        System.out.println(id.workerId);
    }

}
