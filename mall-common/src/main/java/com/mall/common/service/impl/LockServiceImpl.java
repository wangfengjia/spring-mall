package com.mall.common.service.impl;


import com.mall.common.service.ILockService;
import com.mall.common.vo.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class LockServiceImpl implements ILockService {

    private static final long LOCK_EXPIRE = 2 * 1000L; //单个业务持有锁的时间为2s,防止死锁

    private static final long LOCK_TRY_INTERVAL = 30L; //默认30s尝试一次

    private static final long LOCK_TRY_TIMEOUT = 3 * 1000L; //默认尝试三秒

    @Autowired
    private StringRedisTemplate redisTemplate;

    /*
    * 操作redis获取全局锁
    *
    * @param lock 锁的名称
    * @param timeout 获取锁的超时时间
    * @param tryInterval 多少ms尝试一次
    * @param lockExpireTime 锁的过期时间
    * @return true表示获取成功,false表示获取失败
    */
    @Override
    public boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        try {
            if (StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())){
                return false;
            }
            long startTime = System.currentTimeMillis();
            while (true){
                if (redisTemplate.opsForValue().setIfAbsent(lock.getName(), lock.getValue())){
                    redisTemplate.opsForValue().set(lock.getName(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
                    log.info(Thread.currentThread().getName() + " : get lock");
                    return true;
                }else {
                    log.info(Thread.currentThread().getName() + " : locking is exist!!!");
                }

                if (System.currentTimeMillis() - startTime > timeout){
                    return false;
                }

                Thread.sleep(tryInterval);
            }
        }catch (Exception exception){
            log.error(exception.getMessage());
            return false;
        }
    }

    /*
    * 获取全局锁
    * @param lock 锁的名称
    * @return true表示获取成功,false表示获取失败
    */
    @Override
    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /*
    * 获取全局锁
    * @param lock 锁的名称
    * @param timeout 获取锁的超时时间
    * @return true表示获取成功,false表示获取失败
    */
    @Override
    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /*
    * 获取全局锁
    * @param lock 锁的名称
    * @param timeout 获取锁的超时时间
    * @param tryInteval 多少ms尝试一次
    * @return true表示获取成功,false表示获取失败
    *
    */
    @Override
    public boolean tryLock(Lock lock, long timeout, long tryInteval) {
        return getLock(lock, timeout, tryInteval, LOCK_EXPIRE);
    }

    /*
    * 获取全局锁
    * @param lock 锁的名称
    * @params timeout 获取锁的超时时间
    * @param tryInterval 多少ms尝试一次
    * @param lockExpireTime 锁的过期时间
    * @return true表示获取成功,false表示获取失败
    */
    @Override
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }


    /*
    * 释放锁
    * @param lock 锁的名称
    */
    @Override
    public void releaseLock(Lock lock) {
        if (!StringUtils.isEmpty(lock.getName())){
            log.info(Thread.currentThread().getName() + " : delete lock");
            redisTemplate.delete(lock.getName());
        }
    }
}
