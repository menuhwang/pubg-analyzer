package com.menu.pubganalyzer.config.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class CustomThreadPoolConfig {
    @Bean(name = "sqlExecutor")
    public TaskExecutor sqlExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("sql-exec-");
        executor.setTaskDecorator(new ClonedTaskDecorator());
        executor.initialize();
        return executor;
    }

    @Bean(name = "pubgApiExecutor")
    public TaskExecutor pubgApiExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("pubg-api-exec-");
        executor.setTaskDecorator(new ClonedTaskDecorator());
        executor.initialize();
        return executor;
    }
}
