package com.zsc.mall1.service;


import org.springframework.stereotype.Service;

@Service
public class Result {

    private static  Integer statusCode = -1;

    public static Integer getStatusCode() {
        return statusCode;
    }

    public static void setStatusCode(Integer statusCode) {
        Result.statusCode = statusCode;
    }
}
