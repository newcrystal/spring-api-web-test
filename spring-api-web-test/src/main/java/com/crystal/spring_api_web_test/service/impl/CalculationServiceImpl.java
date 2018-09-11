package com.crystal.spring_api_web_test.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import com.crystal.spring_api_web_test.service.CalculationService;

@Service
public class CalculationServiceImpl implements CalculationService {
	@Autowired
	private RetryServiceImpl retryService;
	
	/*public void process(int request) {
		try {
			Thread.sleep(2000);
			System.out.println("proces - " + Thread.currentThread().getName());
			retryService.retryableProcess(request);
			System.out.println("process completed.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
	
	public void process(int request) {
		try {
			SimpleRetryPolicy policy = new SimpleRetryPolicy(5, Collections.singletonMap(Exception.class, true));
			RetryTemplate template = new RetryTemplate();
			template.setRetryPolicy(policy);
			
			FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
			backOffPolicy.setBackOffPeriod(3000);
			
			template.setBackOffPolicy(backOffPolicy);
			template.registerListener(new RetryListener() {
				@Override
				public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
					return true;
				}
				
				@Override
				public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
						Throwable throwable) {
					System.out.println("error");
				}
				
				@Override
				public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback,
						Throwable throwable) {
					System.out.println("close");
				}
			});
			
			template.execute(new RetryCallback<Object, Exception>() {
				private int requestTemp = 0;
			    public Object doWithRetry(RetryContext context) throws Exception {
			    	if (requestTemp == 2) {
			    		return null;
			    	} else {
				    	requestTemp++;
			    		throw new Exception();
			    	}
			    }
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
