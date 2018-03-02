package com.jianghw.app.refresh.api;

import android.support.annotation.NonNull;

import com.jianghw.app.refresh.constant.RefreshState;

/**
 * 刷新布局核心功能接口
 * 为功能复杂的 Header 或者 Footer 开放的接口
 */

public interface RefreshKernelable {
    @NonNull
    RefreshLayoutable getRefreshLayou();

    @NonNull
    RefreshLayoutable getRefreshContent();

    RefreshKernelable setState(@NonNull RefreshState state);
}
