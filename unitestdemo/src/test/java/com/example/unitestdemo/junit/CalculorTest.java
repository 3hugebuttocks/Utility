package com.example.unitestdemo.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalculorTest {
	Calculor mCalculor;

	@BeforeClass
	public static void init(){

	}

	@AfterClass
	public static void destroy(){

	}

	@Before
	public void setUp(){
		mCalculor = new Calculor();
	}

	@After
	public void endUp(){
		mCalculor = null;
	}

	@Test(timeout = 1000)//如果一个测试用例执行的毫秒数超过了指定的参数值，那么 Junit 将自动将它标记为失败。
	public void testAdd(){
		int sum = mCalculor.add(5,6);
		Assert.assertEquals(11, sum);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDivide(){
		mCalculor.divide(4,0);
	}

	@Test
	@Ignore("not implemented yet")
	public void testFactorial() {
	}

}
