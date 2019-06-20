package com.example.looking.managers;

import com.example.looking.eventbus.EventBusContainer;
import com.example.looking.reqest.IRequestCallBack;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RequestManager {
    private Retrofit mRetrofit;

    @Inject
    public RequestManager(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    public <T extends Object> Disposable makeRxSingleRequest(Single<T> single, IRequestCallBack reuestCallback) {
        return single.doOnSubscribe(disposable -> reuestCallback.onStartRequest())
                .doFinally(() -> reuestCallback.onFinishRequest())
                .subscribe(s -> reuestCallback.onSuccess(new EventBusContainer<T>(s)), error -> reuestCallback.onErrorRequest(error.getLocalizedMessage()));
    }

    public <T extends Object> Single<T> makeSingleNewThreadMainSubscriber(Single<T> single) {
        return single.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
