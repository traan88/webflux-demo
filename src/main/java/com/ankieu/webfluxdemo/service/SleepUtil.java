package com.ankieu.webfluxdemo.service;

import org.springframework.stereotype.Service;

@Service
public class SleepUtil {

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
