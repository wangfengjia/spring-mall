package com.mall.common.service;

import com.mall.common.vo.Lock;

public interface ILockService {

    boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime);

    boolean tryLock(Lock lock);

    boolean tryLock(Lock lock, long timeout);

    boolean tryLock(Lock lock, long timeout, long tryInteval);

    boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime);

    void releaseLock(Lock lock);
}
