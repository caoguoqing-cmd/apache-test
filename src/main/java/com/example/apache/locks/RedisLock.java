package com.example.apache.locks;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author caogq
 * @description
 * @create 2021/9/29 17:45
 */
public abstract class RedisLock implements Lock {

    protected Jedis jedis;

    protected String lockKey;

//    public RedisLock(Jedis jedis,String lockKey) {
//        this(jedis, lockKey);
//    }


    public void sleepBySencond(int sencond){
        try {
            Thread.sleep(sencond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void lockInterruptibly(){}

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit){
        return false;
    }

}
