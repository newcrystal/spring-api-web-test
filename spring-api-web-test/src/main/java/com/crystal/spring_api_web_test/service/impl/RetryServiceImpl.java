package com.crystal.spring_api_web_test.service.impl;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryServiceImpl {
	@Retryable(value=ArithmeticException.class, maxAttempts=2, backoff=@Backoff(delay=10000))
	public void retryableProcess(int request) {
		try {
			System.out.println("request : " +request);
			System.out.println(0/(request%2));
		} catch (ArithmeticException e) {
			e.printStackTrace();
			throw e;
		} finally {
			request ++;
		}
	}
	
	@Recover
	public void recover(ArithmeticException e) {
		System.out.println("recovered : " + e.getMessage() + ", thread : "+ Thread.currentThread().getName());
	}
}