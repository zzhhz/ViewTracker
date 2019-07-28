package com.zzh.lib.view.impl;

import android.view.View;

import com.zzh.lib.view.BaseViewUpdater;


/**
 * 设置监听对象到view中来实现实时刷新
 * <p>
 * 监听对象：{@link View.OnLayoutChangeListener}
 */
public class OnLayoutChangeUpdater extends BaseViewUpdater
{
    @Override
    protected boolean startImplemention()
    {
        final View view = getView();
        if (view == null)
            return false;

        view.removeOnLayoutChangeListener(mListener);
        view.addOnLayoutChangeListener(mListener);
        return true;
    }

    @Override
    protected void stopImplemention()
    {
        final View view = getView();
        if (view == null)
            return;

        view.removeOnLayoutChangeListener(mListener);
    }

    private final View.OnLayoutChangeListener mListener = new View.OnLayoutChangeListener()
    {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
        {
            notifyUpdatable();
        }
    };
}
