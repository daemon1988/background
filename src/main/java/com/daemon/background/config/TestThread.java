//package com.daemon.background.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ScheduledFuture;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * 功能描述
// *
// * @author dell
// * @date 2019/12/26 13:43
// */
////@Component
//    @Configuration
//public class TestThread {
//    private String cron;
//    @Autowired
//    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
//    private ScheduledFuture future;
//
//    public void startCron() {
//        cron = "0/3 * * * * ?";
//        System.out.println(Thread.currentThread().getName());
//        String name = Thread.currentThread().getName();
//        future = threadPoolTaskScheduler.schedule(new myTask(name), new CronTrigger(cron));
//    }
//
//    public void stop() {
//        if (future != null) {
//            future.cancel(true);
//        }
//    }
//
//    private class myTask implements Runnable {
//        private String name;
//
//        myTask(String name) {
//            this.name = name;
//        }
//
//        @Override
//        public void run() {
//            System.out.println("test" + name);
//        }
//    }
//
//    @Bean("reconciliationExecutor")
//    public TaskScheduler taskExecutor() {
//        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
//        executor.setPoolSize(4);
//        executor.setThreadNamePrefix("Reconciliation-Executor-");
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //调度器shutdown被调用时等待当前被调度的任务完成
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        //等待时长
//        executor.setAwaitTerminationSeconds(60);
//        return executor;
//    }
//
//
//}