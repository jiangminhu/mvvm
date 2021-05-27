package com.example.baselib.livebus;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentHashMap;

public class LiveEventBus {

    private final ConcurrentHashMap<Object, LiveEvent<Object>> mEventMap;

    private LiveEventBus() {
        mEventMap = new ConcurrentHashMap<>();
    }

    /**
     * 通过单列获取
     */
    private static final class SingleHolder {
        private static final LiveEventBus INSTANCE = new LiveEventBus();
    }

    public static LiveEventBus get() {
        return SingleHolder.INSTANCE;
    }

    //指定key
    public <T> LiveEvent<T> with(@NotNull final String key, @NotNull Class<T> clazz) {
        return realWith(key, clazz);
    }

    //对象类型当key
    public <T> LiveEvent<T> with(@NotNull Class<T> clazz) {
        return realWith(null, clazz);
    }

    /**
     * 存储对象
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> LiveEvent<T> realWith(final String key, Class<T> clazz) {
        //声明key
        final Object objectKey;
        //是否指定key
        if (key != null) {
            objectKey = key;
        } else if (clazz != null) {
            //对象类当key
            objectKey = clazz;
        } else {
            throw new IllegalArgumentException("至少声明一个key");
        }
        //判断是否当前值已存在
        LiveEvent<Object> result = mEventMap.get(objectKey);
        if (result != null) return (LiveEvent<T>) result;
        //防止多线程值混乱
        synchronized (mEventMap) {
            result = mEventMap.get(objectKey);
            if (result == null) {
                result = new LiveEvent<>();
                mEventMap.put(objectKey, result);
            }
        }
        return (LiveEvent<T>) result;

    }


    /**
     * 如果该类型没有观察者，则移除掉
     */
    public void clearData() {
        for (Object next : mEventMap.keySet()) {
            if (mEventMap.get(next) == null || (!mEventMap.get(next).hasObservers())) {
                mEventMap.remove(next);
            }
        }
    }

    public void toValue() {
        Log.e("TGA", "----------toValue------->"+mEventMap.size());
        for (Object next : mEventMap.keySet()) {
            Log.e("TGA",next+"----------toValue------->"+mEventMap.get(next));
        }
    }
}
