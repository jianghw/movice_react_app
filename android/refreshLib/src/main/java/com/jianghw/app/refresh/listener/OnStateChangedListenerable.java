package com.jianghw.app.refresh.listener;

import android.support.annotation.NonNull;

import com.jianghw.app.refresh.api.RefreshLayoutable;
import com.jianghw.app.refresh.constant.RefreshState;

/**
 * 刷新状态改变监听器
 */

public interface OnStateChangedListenerable {
    /**
     * 状态改变事件 {@link RefreshState}
     *
     * @param layoutable
     * @param oldState
     * @param newState
     */
    void onStateChanged(@NonNull RefreshLayoutable layoutable,
                        @NonNull RefreshState oldState,
                        @NonNull RefreshState newState);
}
