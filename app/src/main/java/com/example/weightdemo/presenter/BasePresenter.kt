package com.example.weightdemo.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

open class BasePresenter : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {
        Log.e("TGA", "BasePresenter onCreate--------->")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestory(target : Object) {
        Log.e("TGA", "BasePresenter onCreate--------->$target")
    }
}