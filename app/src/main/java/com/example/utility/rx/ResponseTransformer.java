package com.example.utility.rx;

import com.ecarx.multimedia.entity.onlinemedia.Result;
import com.ecarx.multimedia.utils.T;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by caoyouqiang on 18-5-16.
 */

public class ResponseTransformer {

    public static <T>ObservableTransformer<Result<T>, T> handleResult(){
        return new ObservableTransformer<Result<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<Result<T>> upstream) {
                return upstream.onErrorResumeNext(new ErrorResumeFunction<T>())
                        .flatMap(new ResultFunction<T>());
            }
        };
    }

    public static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends Result<T>>>{
        @Override
        public ObservableSource<? extends Result<T>> apply(@NonNull Throwable throwable) throws Exception {
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    private static class ResultFunction<T> implements Function<Result<T>, ObservableSource<T>>{
        @Override
        public ObservableSource<T> apply(@NonNull Result<T> tResult) throws Exception {
            int code = tResult.getStatus();
            String message = tResult.getMsg();
            if (code == 200){
                return Observable.just(tResult.getData());
            }else {
                return Observable.error(new ApiException(code, message));
            }
        }
    }
}
