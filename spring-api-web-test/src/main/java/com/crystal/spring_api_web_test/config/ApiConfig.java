package com.crystal.spring_api_web_test.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableRetry
@EnableAsync
public class ApiConfig {
	private Log log = LogFactory.getLog(ApiConfig.class);
	
	@Bean
	ThreadPoolTaskExecutor executor () { 
		ThreadPoolTaskExecutor executor =  new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(32);
		executor.setMaxPoolSize(200);
		executor.setKeepAliveSeconds(30);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}
	
	@Bean public RetryListener retryListener() {
        return new RetryListener() {

			@Override
			public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
				log.debug("open : " + Thread.currentThread().getName());
				return true;
			}

			@Override
			public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback,
					Throwable throwable) {
				log.debug("close : " + Thread.currentThread().getName());
				
			}

			@Override
			public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
					Throwable throwable) {
				log.debug("error : " + Thread.currentThread().getName());
			}
        	
        };
    }
}
