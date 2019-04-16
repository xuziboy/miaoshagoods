package com.xuzi.Concurrency.domain;

public class Testcon {

    private String msg;

    public String getMsg(){
        System.out.println("this is auto running");
        return getMsg();
    }
}
