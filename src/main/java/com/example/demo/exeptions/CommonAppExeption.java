package com.example.demo.exeptions;

public class CommonAppExeption extends RuntimeException{
    String s;

    public CommonAppExeption(String s) {
        super(s);
    }
}
