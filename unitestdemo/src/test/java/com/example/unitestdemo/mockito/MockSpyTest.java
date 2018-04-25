package com.example.unitestdemo.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockSpyTest {
	@Test
	public void testSpy(){
		List<String> list = new ArrayList<>();
		List<String> spyList = Mockito.spy(list);

		spyList.add("one");
		spyList.add("two");

		Mockito.verify(spyList).add("one");
		Mockito.verify(spyList).add("two");

		Assert.assertEquals(2, spyList.size());
	}

	@Test
	public void testMock(){
		List<String> list = Mockito.mock(List.class);
		list.add("one");
		list.add("two");

		Mockito.verify(list).add("one");
		Mockito.verify(list).add("two");

		//Assert.assertEquals(2, list.size());
	}
}
