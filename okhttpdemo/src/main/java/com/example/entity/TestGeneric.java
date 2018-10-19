package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
    public static void main(String... args){
        List<? extends Fruit> flist = new ArrayList<Apple>();


        Class aClass = new ArrayList<Integer>().getClass();
        Class aClass1 = new ArrayList<String>().getClass();

        System.out.println(aClass == aClass1);

        List<? super Fuji> list = new ArrayList<Apple>();
        list.add(new Fuji());
    }
}
