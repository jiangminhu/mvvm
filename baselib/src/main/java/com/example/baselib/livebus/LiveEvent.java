package com.example.baselib.livebus;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

public class LiveEvent<T> {
    final static int START_VALUE = -1;
    final Object mDataLock = new Object();
    private static final Object NOT_SET = new Object();
    private final SafeIterableMap<Observer<? super T>, ObserverWrapper> mObservers = new SafeIterableMap<>();
    private int mVersion = START_VALUE;

    private volatile Object mData = NOT_SET;
    volatile Object mPendingData = NOT_SET;

    private boolean mDispatchingValue;
    private boolean mDispatchInvalidated;

    private final Runnable runnable = () -> {
        Object newValue;
        synchronized (mDataLock) {
            newValue = mPendingData;
            mPendingData = NOT_SET;
        }
        setValue((T) newValue);
    };


    private void dispatchingValue(ObserverWrapper initiator) {
        //是否正在分发数据
        if (mDispatchingValue) {
            mDispatchInvalidated = true;
            return;
        }
        mDispatchingValue = true;
        do {
            mDispatchInvalidated = false;
            //判断是否是单个观察者生命周期变化引起的数据刷新
            if (initiator != null) {
                considerNotify(initiator);
                initiator = null;
            } else {
                //分发所有观察者
                for (Iterator<Map.Entry<Observer<? super T>, ObserverWrapper>> iterator = mObservers.iteratorWithAdditions(); iterator.hasNext(); ) {
                    considerNotify(iterator.next().getValue());
                    if (mDispatchInvalidated) {
                        break;
                    }
                }
            }
        } while (mDispatchInvalidated);
        mDispatchingValue = false;
    }

    //当前观察者信息
    private void considerNotify(ObserverWrapper observer) {
        //是否活跃
        if (!observer.mActive) {
            return;
        }
        //生命周期是否处于活跃
        if (!observer.shouldBeActive()) {
            observer.activeStateChange(false);
            return;
        }
        //是否已通知过
        if (observer.mLastVision >= mVersion) {
            return;
        }
        observer.mLastVision = mVersion;
        //分发事件
        observer.mObserver.onChanged((T) mData);

    }

    /**
     * 正常初始化  不接受历史值
     *
     * @param owner
     * @param observer
     */
    @MainThread
    public void observe(@NotNull LifecycleOwner owner, @NotNull Observer<? super T> observer) {
        assertMainThread("observe");
        realObserverCustom(owner, Lifecycle.State.CREATED, observer, false);
    }

    /**
     * 正常初始化  接受历史值
     *
     * @param owner
     * @param observer
     */
    @MainThread
    public void observeSticky(@NotNull LifecycleOwner owner, @NotNull Observer<? super T> observer) {
        assertMainThread("observeSticky");
        realObserverCustom(owner, Lifecycle.State.CREATED, observer, true);
    }

    @MainThread
    public void observeCustom(@NotNull LifecycleOwner owner, @NotNull Lifecycle.State state, @NotNull Observer<? super T> observer) {
        assertMainThread("observeCustom");
        realObserverCustom(owner, state, observer, false);
    }

    @MainThread
    public void observeCustomSticky(@NotNull LifecycleOwner owner, @NotNull Lifecycle.State state, @NotNull Observer<? super T> observer) {
        assertMainThread("observeCustomSticky");
        realObserverCustom(owner, state, observer, true);
    }

    /**
     * 注册观察者
     *
     * @param owner       当前生命周期对象
     * @param state       当前生命周期状态
     * @param observer    观察对象
     * @param isStickMode 是否接收历史消息
     */
    private void realObserverCustom(@NotNull LifecycleOwner owner, @NotNull Lifecycle.State state, @NotNull Observer<? super T> observer, boolean isStickMode) {
        //当前对象已销毁就没必要观察了
        if (owner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        //二次包装，
        CustomerActiveObserver wrapper = new CustomerActiveObserver(owner, state, observer, isStickMode);
        ObserverWrapper existing = mObservers.putIfAbsent(observer, wrapper);
        if (existing != null && !existing.isAttachedTo(owner)) {
            throw new IllegalArgumentException("Cannot add the same observer"
                    + " with different lifecycles");
        }
        if (existing != null) {
            return;
        }
        owner.getLifecycle().addObserver(wrapper);
    }


    @MainThread
    public void observeForever(@NotNull Observer<? super T> observer) {
        assertMainThread("observeForever");
        realObserveForever(observer, false);
    }

    @MainThread
    public void observeForeverSticky(@NotNull Observer<? super T> observer) {
        assertMainThread("observeForever");
        realObserveForever(observer, true);
    }


    private void realObserveForever(Observer<? super T> observer, boolean isSticky) {
        AlwaysActiveObserver wrapper = new AlwaysActiveObserver(observer, isSticky);
        ObserverWrapper existing = mObservers.putIfAbsent(observer, wrapper);
        if (existing instanceof LiveEvent.CustomerActiveObserver) {
            throw new IllegalArgumentException("Cannot add the same observer"
                    + " with different lifecycles");
        }
        if (existing != null) {
            return;
        }
        wrapper.activeStateChange(true);
    }


    @MainThread
    public void setValue(T value) {
        assertMainThread("setValue");
        mVersion++;
        mData = value;
        dispatchingValue(null);
    }

    public void postValue(T value) {
        //主线程不需要切换线程
        if (DefaultTaskExecutor.getInstance().isMainThread()) {
            setValue(value);
        } else {
            //通过handler切换线程
            boolean postTask;
            synchronized (mDataLock) {
                postTask = mPendingData == NOT_SET;
                mPendingData = value;
            }
            if (!postTask) {
                return;
            }
            DefaultTaskExecutor.getInstance().postToMainThread(runnable);
        }
    }

    /**
     * 是否还有观察者
     *
     * @return
     */
    public boolean hasObservers() {
        return mObservers.size() > 0;
    }

    @MainThread
    public void removeObserver(@NotNull final Observer<? super T> mObserver) {
        assertMainThread("removeObserver");
        ObserverWrapper removed = mObservers.remove(mObserver);
        if (removed == null) {
            return;
        }
        removed.detachObserver();
        removed.activeStateChange(false);
    }

    @MainThread
    public void removeObservers(@NotNull final LifecycleOwner owner) {
        assertMainThread("removeObservers");
        for (Map.Entry<Observer<? super T>, ObserverWrapper> entry : mObservers) {
            if (entry.getValue().isAttachedTo(owner)) {
                removeObserver(entry.getKey());
            }
        }
    }


    private abstract class ObserverWrapper {
        final Observer<? super T> mObserver;
        boolean mActive;
        final boolean isStickyMode;//是否是粘性事件
        int mLastVision = START_VALUE;

        public ObserverWrapper(Observer<? super T> mObserver, boolean isStickyMode) {
            this.mObserver = mObserver;
            this.isStickyMode = isStickyMode;
        }

        abstract boolean shouldBeActive();

        boolean isAttachedTo(LifecycleOwner owner) {
            return false;
        }

        void detachObserver() {

        }

        void activeStateChange(boolean newActive) {
            Log.e("TGA",mActive+"-------------->"+newActive);
            if (newActive == mActive) {
                return;
            }
            mActive = newActive;
            if (mActive && isStickyMode) {
                dispatchingValue(this);
            }
        }
    }

    /**
     * 可变化的观察者（activity/fragment）
     */
    private class CustomerActiveObserver extends ObserverWrapper implements LifecycleEventObserver {
        @NotNull
        final Lifecycle.State mState;
        @NotNull
        final LifecycleOwner mOwner;

        public CustomerActiveObserver(@NotNull LifecycleOwner mOwner, @NotNull Lifecycle.State mState, Observer<? super T> mObserverWrapper, boolean isStickyMode) {
            super(mObserverWrapper, isStickyMode);
            this.mState = mState;
            this.mOwner = mOwner;
        }

        @Override
        boolean shouldBeActive() {
            //判断当前观察者是否处于活跃状态
            return mOwner.getLifecycle().getCurrentState().isAtLeast(mState);
        }

        @Override
        public void onStateChanged(@NonNull @NotNull LifecycleOwner source, @NonNull @NotNull Lifecycle.Event event) {
            if (mOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                removeObserver(mObserver);
                return;
            }
            activeStateChange(shouldBeActive());
        }

        @Override
        boolean isAttachedTo(LifecycleOwner owner) {
            return mOwner == owner;
        }

        @Override
        void detachObserver() {
            mOwner.getLifecycle().removeObserver(this);
        }
    }

    /**
     * 永远处于活动状态的观察者
     */
    private class AlwaysActiveObserver extends ObserverWrapper {

        public AlwaysActiveObserver(Observer<? super T> mObserverWrapper, boolean isStickyMode) {
            super(mObserverWrapper, isStickyMode);
        }

        @Override
        boolean shouldBeActive() {
            return true;
        }

    }

    /**
     * 检查是否为主线程
     *
     * @param methodName
     */
    private static void assertMainThread(String methodName) {
        if (!DefaultTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot invoke " + methodName + " on a background"
                    + " thread");
        }
    }
}
