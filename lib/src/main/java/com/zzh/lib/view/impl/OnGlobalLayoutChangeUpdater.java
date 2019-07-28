package com.zzh.lib.view.impl;

import android.os.Build;
import android.view.ViewTreeObserver;

import com.zzh.lib.view.ViewTreeObserverUpdater;


/**
 * 设置监听对象到view中来实现实时刷新
 * <p>
 * 监听对象：{@link ViewTreeObserver.OnGlobalLayoutListener}
 */
public class OnGlobalLayoutChangeUpdater extends ViewTreeObserverUpdater
{
    @Override
    protected void register(ViewTreeObserver observer)
    {
        observer.addOnGlobalLayoutListener(mListener);
    }

    @Override
    protected void unregister(ViewTreeObserver observer)
    {
        if (Build.VERSION.SDK_INT >= 16)
            observer.removeOnGlobalLayoutListener(mListener);
        else
            observer.removeGlobalOnLayoutListener(mListener);
    }

    private final ViewTreeObserver.OnGlobalLayoutListener mListener = new ViewTreeObserver.OnGlobalLayoutListener()
    {
        @Override
        public void onGlobalLayout()
        {
            notifyUpdatable();
        }
    };
}
