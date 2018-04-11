package com.example.testapp;

public class Calculor {
	public int add(int one, int another) {
		return one + another;
	}

	public double divide(double divident, double dividor) {
		if (dividor == 0) throw new IllegalArgumentException("Dividor cannot be 0");

		return divident / dividor;
	}
}
