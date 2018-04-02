package com.example.utility.databinding;

/**
 * Created by caoyouqiang on 18-4-2.
 */

public class User {
	private String id;
	private String name;
	private String blog;

	public User(String id, String name, String blog) {
		this.id = id;
		this.name = name;
		this.blog = blog;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getBlog() {
		return this.blog;
	}
}
