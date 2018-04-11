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

	@Test
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
