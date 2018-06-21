package com.example.springbootdemo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {
    private int count = 0;

    @Scheduled(fixedDelay = 6000)
    public void run() {
        System.out.println("启动服务后，定时执行任务,次数="+count++);
    }
}
