package com.xuzi.Concurrency.redis;

public class ConUserKey extends BasePrefix {
    private static final int TOKEN_EXPIRE = 3600*24*2;
    private ConUserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static ConUserKey token = new ConUserKey(TOKEN_EXPIRE,"tk");
}
