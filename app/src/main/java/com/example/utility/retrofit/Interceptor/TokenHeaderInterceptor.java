package com.example.utility.retrofit.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenHeaderInterceptor implements Interceptor{
	@Override
	public Response intercept(Chain chain) throws IOException {
		String token = "";
		Request originalRequest = chain.request();
		Request updateRequest = originalRequest.newBuilder()
				.header("token", token)
				.build();
		return chain.proceed(updateRequest);
	}
}
