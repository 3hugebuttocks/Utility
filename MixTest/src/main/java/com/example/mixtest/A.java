package com.example.mixtest;


class A {
    public void print() {
        System.out.println("helloworld");
    }

    public int print(int a, int b) {
        System.out.println(a + b);
        return a+b;
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());
    }
}
