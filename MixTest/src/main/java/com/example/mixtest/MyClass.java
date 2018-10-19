package com.example.mixtest;

import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MyClass {
    public static void main(String[] main) throws InterruptedException {
        Observable.just(1,2,3,4,5,6)
                .subscribeOn(Schedulers.computation())
                .doOnNext(integer -> System.out.println("Emitting item " + integer + " on: " + Thread.currentThread().getName()))
                .subscribe(integer -> System.out.println("Consuming item " + integer + " on: " + Thread.currentThread().getName()));

        System.out.println("main thread name: " + Thread.currentThread().getName());
        Thread.sleep(5000);
    }

}
