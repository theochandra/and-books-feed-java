package com.android.booksfeedjava.domain.usecase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("ALL")
public abstract class UseCase<T> {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public DisposableObserver execute(DisposableObserver useCaseSubscriber) {
        DisposableObserver disposableObserver = buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(useCaseSubscriber);
        mCompositeDisposable.add(disposableObserver);
        return disposableObserver;
    }

    protected abstract Observable<T> buildUseCaseObservable();

    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
    }

}
