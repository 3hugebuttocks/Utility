package com.example.annotationtest;

@Exportable("address")
public class AddressForTest {
	@Persistent
	private String country = null;

	@Persistent
	private String province = null;

	@Persistent
	private String city = null;

	@Persistent
	private String street = null;

	@Persistent
	private String doorplate = null;

	public AddressForTest(String country, String province,
						  String city, String street, String doorplate) {
		this.country = country;
		this.province = province;
		this.city = city;
		this.street = street;
		this.doorplate = doorplate;
	}
}
