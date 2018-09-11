package com.crystal.spring_api_web_test;

import org.junit.Test;

public class SimpleTest {
	private int num = 1;
	@Test
	public void test() {
		try {
			System.out.println(0/num);
		} catch (ArithmeticException e) {
			e.printStackTrace();
		} finally {
			num ++;
		}
	}
}
