package com.xuzi.Concurrency.redis;

public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    @Override
    public int expireSeconds() { // 默认0 永不过期
        return expireSeconds;
    }
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}
