package com.example.rxlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "Leo";
    private volatile PublishSubject<Integer> mUpdateSubject = PublishSubject.create();
    private static long START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        START = System.currentTimeMillis();
        mUpdateSubject.throttleFirst(10000, TimeUnit.MILLISECONDS).subscribe(getUpdateStateConsumer());

        /*Random random = new Random();
        for (int i = 0; i < 15; i++){
            Log.d(TAG, " " + random.nextInt(5));
        }*/
    }

    private Consumer<Integer> getUpdateStateConsumer(){
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer v) throws Exception {
                Log.d(TAG, "dura: " + (System.currentTimeMillis() - START)/*+";"+new SimpleDateFormat("HH:mm:ss SSS").format(new Date())*/);
                Log.d(TAG, "accept: " + v);
            }
        };
    }

    public static int i=0;
    public void onClick(View view){
        /*ExecutorService service = Executors.newSingleThreadExecutor();
        START = System.currentTimeMillis();

        service.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    mUpdateSubject.onNext(i);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/
        START = System.currentTimeMillis();
        /*if(i==0){
            Log.d(TAG, "first: " + new SimpleDateFormat("HH:mm:ss SSS").format(new Date(START)));
        }*/


        mUpdateSubject.onNext(i++);
    }
}