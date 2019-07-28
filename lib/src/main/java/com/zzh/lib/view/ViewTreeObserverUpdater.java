package com.zzh.lib.view;

import android.view.View;
import android.view.ViewTreeObserver;

public abstract class ViewTreeObserverUpdater extends BaseViewUpdater
{
    @Override
    protected void onViewChanged(View oldView, View newView)
    {
        super.onViewChanged(oldView, newView);
        if (oldView != null)
            oldView.removeOnAttachStateChangeListener(mOnAttachStateChangeListener);
    }

    private final View.OnAttachStateChangeListener mOnAttachStateChangeListener = new View.OnAttachStateChangeListener()
    {
        @Override
        public void onViewAttachedToWindow(View v)
        {
            if (isStarted())
                startImplemention();
        }

        @Override
        public void onViewDetachedFromWindow(View v)
        {
        }
    };

    @Override
    protected void onStateChanged(boolean started)
    {
        super.onStateChanged(started);

        final View view = getView();
        if (view != null)
        {
            view.removeOnAttachStateChangeListener(mOnAttachStateChangeListener);
            if (started)
                view.addOnAttachStateChangeListener(mOnAttachStateChangeListener);
        }
    }

    @Override
    protected boolean startImplemention()
    {
        final View view = getView();
        if (view == null)
            return false;

        final ViewTreeObserver observer = view.getViewTreeObserver();
        if (!observer.isAlive())
            return false;

        unregister(observer);
        register(observer);
        return true;
    }

    @Override
    protected void stopImplemention()
    {
        final View view = getView();
        if (view == null)
            return;

        final ViewTreeObserver observer = view.getViewTreeObserver();
        if (!observer.isAlive())
            return;

        unregister(observer);
    }

    /**
     * 注册监听
     *
     * @param observer
     */
    protected abstract void register(ViewTreeObserver observer);

    /**
     * 取消注册监听
     *
     * @param observer
     */
    protected abstract void unregister(ViewTreeObserver observer);
}
