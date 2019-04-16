package com.xuzi.Concurrency.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
