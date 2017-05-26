package com.techmagic.kotlinexample.domain.usecase;

import com.techmagic.kotlinexample.data.manager.Manager;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Roman Ursu on 5/26/17
 */

public abstract class BaseUseCase<REQUEST_DATA, RESPONSE_DATA, MANAGER extends Manager> {
    private MANAGER manager;
    private Scheduler workerThreadScheduler;
    private Scheduler postExecutionScheduler;
    private CompositeSubscription subscription = new CompositeSubscription();

    public BaseUseCase(MANAGER manager, Scheduler threadScheduler, Scheduler postExecutionScheduler) {
        this.manager = manager;
        this.workerThreadScheduler = threadScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    protected abstract Observable<RESPONSE_DATA> buildObservable(REQUEST_DATA requestData);

    public void execute(REQUEST_DATA requestData, Subscriber<RESPONSE_DATA> useCaseSubscriber) {
        this.subscription.add(this.buildObservable(requestData)
                .subscribeOn(workerThreadScheduler)
                .observeOn(postExecutionScheduler)
                .subscribe(useCaseSubscriber));
    }

    public boolean isUnsubscribed() {
        return !subscription.hasSubscriptions();
    }

    public void unsubscribe() {
        if (!isUnsubscribed()) {
            subscription.clear();
        }
    }
}
