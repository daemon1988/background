/*

package com.daemon.background.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;


*/
/**
 * 功能描述
 *
 * @author dell
 * @date 2019/12/26 13:40
 *//*


@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    private ScheduledFuture future;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (taskRegistrar.getScheduledTasks() == null) {
            taskRegistrar.setTaskScheduler(taskExecutor());
            this.future = taskRegistrar.getScheduler().schedule(new myTask(), new CronTrigger("* 0/1 * * * ?"));
        }
    }

    @Bean("reconciliationExecutor")
    public TaskScheduler taskExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(4);
        executor.setThreadNamePrefix("Reconciliation-Executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }

    public class myTask implements Runnable {
        int i = 0;
        @Override
        public void run() {
            if (i != 1) {
                i = 1;
                System.out.println("不等于1："+Thread.currentThread().getName());
            } else {
                System.out.println("等于1:"+Thread.currentThread().getName());
                if (future != null) {
                    future.cancel(true);
                }
            }
        }
    }

}
*/
