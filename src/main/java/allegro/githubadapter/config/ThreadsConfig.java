package allegro.githubadapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class ThreadsConfig {

    @Value("${thread.pool.size.core:2}")
    private int corePoolSize;

    @Value("${thread.pool.size.max:8}")
    private int maxPoolSize;

    @Value("${thread.queue.capacity:1000}")
    private int queueCapacity;

    @Value("${thread.prefix:githubapi}")
    private String threadNamePrefix;

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }
}
