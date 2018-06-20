package com.example.rxlearn.hotcold;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

public class RxHotCold {
    private final String TAG = "RxHotCold";

    Consumer<Long> sub1 = new Consumer<Long>() {
        @Override
        public void accept(Long aLong) throws Exception {
            Log.d(TAG, "sub1: " +aLong);
        }
    };

    Consumer<Long> sub2 = new Consumer<Long>() {
        @Override
        public void accept(Long aLong) throws Exception {
            Log.d(TAG, "sub2: " +aLong);
        }
    };

    Consumer<Long> sub3 = new Consumer<Long>() {
        @Override
        public void accept(Long aLong) throws Exception {
            Log.d(TAG, "sub3: " +aLong);
        }
    };

    /**如果使用.publish()创建，那么订阅者只能收到在订阅之后Cold Observable发出的数据，
     * 而如果使用reply(int N)创建，那么订阅者在订阅后可以收到Cold Observable在订阅之前发送的N个数据。
     * @Description:
     * @param:[]
     * @return:void
     * @author:Leo
     * @Date:2018/6/20
     */
    private void rxColdToHot(){
        ConnectableObservable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(11)
                        .subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();

        observable.connect();

        observable.subscribe(sub1);
        observable.subscribe(sub2);

        try {
            Thread.sleep(20L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        observable.subscribe(sub3);

        try {
            Thread.sleep(100L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    /**第一个订阅者订阅到refObservable后，Cold Observable开始发送数据。
     之后的订阅者订阅到refObservable后，只能收到在订阅之后Cold Observable发送的数据。
     如果一个订阅者取消订阅到refObservable后，假如它是当前refObservable的唯一一个订阅者，那么Cold Observable会停止发送数据；
     否则，Cold Observable仍然会继续发送数据，其它的订阅者仍然可以收到Cold Observable发送的数据。

     autoConnect(int N),当有N个订阅者订阅到refObservable后，Cold Observable开始发送数据。
     之后的订阅者订阅到refObservable后，只能收到在订阅之后Cold Observable发送的数据。
     只要Cold Observable开始发送数据，即使所有的autoObservable的订阅和都取消了订阅，
     Cold Observable也不会停止发送数据，如果想要Cold Observable停止发送数据，
     那么可以使用autoConnect(int numberOfSubscribers, Consumer connection)中Consumer返回的Disposable，
     它的作用和ConnectableObservable的connect方法返回的Disposable相同。
     * @Description:
     * @param:[]
     * @return:void
     * @author:Leo
     * @Date:2018/6/20
     */
    private void rxHotToCold(){
        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(11)
                        .subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread());

        /*observable.connect();

        Observable<Long> observable1 = observable.refCount();*/
        Observable<Long> observable1 = observable.share();

        Disposable disposable1 = observable1.subscribe(sub1);
        Disposable disposable2 = observable1.subscribe(sub2);

        try {
            Thread.sleep(20L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        disposable1.dispose();
        disposable2.dispose();

        Log.d(TAG, "rxHotToCold: " + "重新开始数据流");

        disposable1 = observable1.subscribe(sub1);
        disposable2 = observable1.subscribe(sub2);

        try {
            Thread.sleep(20L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /*
    share = publish.refCount()
     */
}
