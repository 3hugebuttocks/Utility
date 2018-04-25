package com.example.annotationtest;

import java.util.ArrayList;

@Exportable(name = "contactbook", description = "contact book")
public class ContactBookForTest {
	@Persistent
	private String friendName = null;

	@Persistent
	private int age = 0;

	@Persistent
	private ArrayList<String> telephones = null;

	@Persistent
	private ArrayList<AddressForTest> AddressForText = null;

	@Persistent
	private String note = null;

	public ContactBookForTest(String name, int age,
							  ArrayList<String> telephoneList,
							  ArrayList<AddressForTest> addressList,
							  String note) {
		this.friendName = name;
		this.age = age;
		this.telephones = new ArrayList<>(telephoneList);
		this.AddressForText = new ArrayList<>(addressList);
		this.note = note;
	}
}
