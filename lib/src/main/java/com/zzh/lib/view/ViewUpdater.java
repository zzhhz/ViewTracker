package com.zzh.lib.view;

import android.view.View;

public interface ViewUpdater
{
    /**
     * 设置要实时更新的对象
     *
     * @param updatable
     */
    void setUpdatable(Updatable updatable);

    /**
     * 通知设置的对象{@link #setUpdatable(Updatable)}进行更新
     */
    void notifyUpdatable();

    /**
     * 设置状态变化回调
     *
     * @param callback
     */
    void setOnStateChangeCallback(OnStateChangeCallback callback);

    /**
     * 设置view变化回调
     *
     * @param callback
     */
    void setOnViewChangeCallback(OnViewChangeCallback callback);

    /**
     * 返回设置的view
     *
     * @return
     */
    View getView();

    /**
     * 设置view
     *
     * @param view
     */
    void setView(View view);

    /**
     * 开始实时更新
     *
     * @return true-成功开始
     */
    boolean start();

    /**
     * 停止实时更新
     */
    void stop();

    /**
     * 是否已经开始实时更新
     *
     * @return
     */
    boolean isStarted();

    interface Updatable
    {
        /**
         * 触发更新
         */
        void update();
    }

    interface OnStateChangeCallback
    {
        /**
         * 是否已经开始实时更新状态变化
         *
         * @param started
         * @param updater
         */
        void onStateChanged(boolean started, ViewUpdater updater);
    }

    interface OnViewChangeCallback
    {
        /**
         * 设置的view变化回调
         *
         * @param oldView
         * @param newView
         * @param updater
         */
        void onViewChanged(View oldView, View newView, ViewUpdater updater);
    }
}
