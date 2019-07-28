package com.zzh.lib.view;

import android.view.View;

import java.lang.ref.WeakReference;

public abstract class BaseViewUpdater implements ViewUpdater
{
    private boolean mIsStarted;
    private Updatable mUpdatable;
    private WeakReference<View> mView;

    private OnStateChangeCallback mOnStateChangeCallback;
    private OnViewChangeCallback mOnViewChangeCallback;

    @Override
    public final void setUpdatable(Updatable updatable)
    {
        mUpdatable = updatable;
    }

    @Override
    public final void notifyUpdatable()
    {
        if (mUpdatable != null)
            mUpdatable.update();
    }

    @Override
    public final void setOnStateChangeCallback(OnStateChangeCallback callback)
    {
        mOnStateChangeCallback = callback;
    }

    @Override
    public final void setOnViewChangeCallback(OnViewChangeCallback callback)
    {
        mOnViewChangeCallback = callback;
    }

    @Override
    public final View getView()
    {
        return mView == null ? null : mView.get();
    }

    @Override
    public final void setView(View view)
    {
        final View old = getView();
        if (old != view)
        {
            stop();

            mView = view == null ? null : new WeakReference<>(view);

            onViewChanged(old, view);
            if (mOnViewChangeCallback != null)
                mOnViewChangeCallback.onViewChanged(old, view, this);
        }
    }

    protected void onViewChanged(View oldView, View newView)
    {
    }

    @Override
    public final boolean start()
    {
        if (isStarted())
            return true;

        setStarted(startImplemention());

        return isStarted();
    }

    @Override
    public final void stop()
    {
        stopImplemention();
        setStarted(false);
    }

    private final void setStarted(boolean started)
    {
        if (mIsStarted != started)
        {
            mIsStarted = started;

            onStateChanged(started);
            if (mOnStateChangeCallback != null)
                mOnStateChangeCallback.onStateChanged(started, this);
        }
    }

    protected void onStateChanged(boolean started)
    {
    }

    @Override
    public final boolean isStarted()
    {
        if (getView() == null)
            setStarted(false);

        return mIsStarted;
    }

    /**
     * 开始实时更新
     *
     * @return true-成功开始
     */
    protected abstract boolean startImplemention();

    /**
     * 停止实时更新
     */
    protected abstract void stopImplemention();
}
