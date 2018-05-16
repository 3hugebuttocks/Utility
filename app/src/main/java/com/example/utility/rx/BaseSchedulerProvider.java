package com.example.utility.rx;

import com.ecarx.multimedia.utils.T;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by caoyouqiang on 18-5-16.
 */

public interface BaseSchedulerProvider {
    Scheduler computation();

    Scheduler io();

    Scheduler ui();

    <T> ObservableTransformer<T, T> applySchedulers();
}
