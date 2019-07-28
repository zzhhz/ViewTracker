package com.zzh.lib.view.impl;

import android.view.ViewTreeObserver;

import com.zzh.lib.view.ViewTreeObserverUpdater;


/**
 * 设置监听对象到view中来实现实时刷新
 * <p>
 * 监听对象：{@link ViewTreeObserver.OnPreDrawListener}
 */
public class OnPreDrawUpdater extends ViewTreeObserverUpdater
{
    @Override
    protected void register(ViewTreeObserver observer)
    {
        observer.addOnPreDrawListener(mListener);
    }

    @Override
    protected void unregister(ViewTreeObserver observer)
    {
        observer.removeOnPreDrawListener(mListener);
    }

    private final ViewTreeObserver.OnPreDrawListener mListener = new ViewTreeObserver.OnPreDrawListener()
    {
        @Override
        public boolean onPreDraw()
        {
            notifyUpdatable();
            return true;
        }
    };
}
