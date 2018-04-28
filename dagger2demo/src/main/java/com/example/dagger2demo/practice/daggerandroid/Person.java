package com.example.dagger2demo.practice.daggerandroid;

import javax.inject.Inject;

public class Person {
	private String name;
	private int age;

	@Inject
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
