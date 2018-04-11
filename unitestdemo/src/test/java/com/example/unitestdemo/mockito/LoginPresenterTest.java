package com.example.unitestdemo.mockito;

import com.example.unitestdemo.share.JSpec;
import com.example.unitestdemo.share.NetworkCallback;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.timeout;

public class LoginPresenterTest {
	@Test
	public void testLogin(){
		UserManager mockUserManager = Mockito.mock(UserManager.class);
		LoginPresenter loginPresenter = new LoginPresenter();
		loginPresenter.setmUserManager(mockUserManager);

		loginPresenter.login("leo", "fksourcecode");

		Mockito.verify(mockUserManager, timeout(1)).performLogin(anyString(), anyString());
	}

	@Test
	@JSpec(desc = "should mock return given value")
	public void test(){
		PasswordValidator validator = Mockito.mock(PasswordValidator.class);
		Mockito.when(validator.verifyPassword("leoleo")).thenReturn(true);

		Assert.assertEquals(true, validator.verifyPassword("leoleo"));

		Mockito.when(validator.verifyPassword(anyString())).thenReturn(true);
		Assert.assertEquals(true, validator.verifyPassword("abcdefg"));
	}

	@Test
	@JSpec(desc = "should mock perform certain action")
	public void testMockAnswer(){
		UserManager mockUserManager = Mockito.mock(UserManager.class);
		Mockito.doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				//这里可以获得传给performLogin的参数
				Object[] arguments = invocation.getArguments();

				NetworkCallback callback = (NetworkCallback) arguments[2];
				callback.onFailure(500, "Server error");
				return 500; //对于如果mock的是非void方法来说，这个将作为目标方法的返回值
			}
		}).when(mockUserManager).performLogin(anyString(), anyString(), any(NetworkCallback.class));

		mockUserManager.performLogin("leo", "aaaa", Mockito.mock(NetworkCallback.class));
	}

	@Test
	public void testMockDoNothing(){
		UserManager mockUserManager = Mockito.mock(UserManager.class);

		Mockito.doNothing().when(mockUserManager).performLogin(anyString(), anyString(), any(NetworkCallback.class));

		mockUserManager.performLogin("leo", "aaaa", Mockito.mock(NetworkCallback.class));
	}

	@Test
	public void testSpy(){
		//跟创建mock类似，只不过调用的是spy方法，而不是mock方法。spy的用法
		PasswordValidator spyValidator = Mockito.spy(PasswordValidator.class);

		//在默认情况下，spy对象会调用这个类的real implementation，并返回相应的返回值
		boolean result = spyValidator.verifyPassword("fksourcecode");//true
		Assert.assertTrue(result);
		result = spyValidator.verifyPassword("fksourcecodenot"); //false
		Assert.assertFalse(result);

		//也可以指定spy对象的方法的行为
		Mockito.when(spyValidator.verifyPassword(anyString())).thenReturn(true);

		result = spyValidator.verifyPassword("xxx");
		Assert.assertTrue(result);
		Mockito.verify(spyValidator).verifyPassword("fksourcecode");
	}
}
