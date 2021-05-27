package com.example.baselib.livebus;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;

public class DefaultTaskExecutor {

    private final Object mLock = new Object();

    @Nullable
    private volatile Handler mMainHandler;

    private static volatile DefaultTaskExecutor sInstance;

    private DefaultTaskExecutor() {

    }

    public static DefaultTaskExecutor getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (DefaultTaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new DefaultTaskExecutor();
            }
        }
        return sInstance;
    }

    //切换到主线程工作
    public void postToMainThread(Runnable runnable) {
        if (mMainHandler == null) {
            synchronized (mLock) {
                if (mMainHandler == null) {
                    mMainHandler = createAsync(Looper.getMainLooper());
                }
            }
        }
        mMainHandler.post(runnable);
    }


    //是否是主线程
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    //不同SDK创建handler实例
    private static Handler createAsync(@NonNull Looper looper) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Handler.createAsync(looper);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                return Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class,
                        boolean.class)
                        .newInstance(looper, null, true);
            } catch (IllegalAccessException ignored) {
            } catch (InstantiationException ignored) {
            } catch (NoSuchMethodException ignored) {
            } catch (InvocationTargetException e) {
                return new Handler(looper);
            }
        }
        return new Handler(looper);
    }
}
