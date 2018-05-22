package com.example.utility.retrofit.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomResponseConverter<T> implements Converter<ResponseBody, T>{
	private final Gson gson;
	private final TypeAdapter<T> adapter;
	private static final String CODE = "code";
	private static final String DATA = "data";

	CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
		this.gson = gson;
		this.adapter = adapter;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			String originalBody = value.string();
			String body = "";//对originalBody进行解密

			JSONObject json = new JSONObject(body);
			int code  = json.optInt(CODE);

			if (code != 200){
				Map<String, String> map = gson.fromJson(body, new TypeToken<Map<String, String>>() {
				}.getType());
				map.put(DATA, null);
				body = gson.toJson(map);
			}
			return adapter.fromJson(body);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}finally {
			value.close();
		}
	}
}
