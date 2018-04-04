package com.example.utility.databinding.bean;

import android.databinding.BaseObservable;

/**
 * Created by caoyouqiang on 18-4-3.
 */

public class Phone extends BaseObservable{
	private String name;
	private String owner;

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
