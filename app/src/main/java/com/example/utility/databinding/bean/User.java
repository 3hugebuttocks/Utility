package com.example.utility.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.utility.BR;

import retrofit2.http.PUT;

/**
 * Created by caoyouqiang on 18-4-2.
 */

public class User extends BaseObservable {
	private String id;
	private String name;
	private String blog;

	public User(){}

	public User(String id, String name, String blog) {
		this.id = id;
		this.name = name;
		this.blog = blog;
	}

	public void setId(String id) {
		this.id = id;
		notifyPropertyChanged(BR.id);
	}

	@Bindable
	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
		notifyPropertyChanged(BR.name);
	}

	@Bindable
	public String getName() {
		return this.name;
	}

	public void setBlog(String blog) {
		this.blog = blog;
		notifyPropertyChanged(BR.blog);
	}

	@Bindable
	public String getBlog() {
		return this.blog;
	}
}
