package com.example.utility.retrofit.Interceptor;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestEncryptInterceptor implements Interceptor{
	private static final String FORM_NAME = "content";
	private static final String CHARSET = "UTF-8";

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		RequestBody body = request.body();
		if (body instanceof FormBody){
			FormBody formBody = (FormBody) body;
			Map<String, String> formMap = new HashMap<>();
			for (int i = 0; i < formBody.size(); i++){
				formMap.put(formBody.name(i), formBody.value(i));
			}

			Gson gson = new Gson();
			String jsonParams = gson.toJson(formMap);
			String encryptParams = "";//经过加密
			body = new FormBody.Builder().add(FORM_NAME, encryptParams).build();
		}
		if (body != null){
			request = request.newBuilder()
					.post(body)
					.build();
		}
		return chain.proceed(request);
	}
}
