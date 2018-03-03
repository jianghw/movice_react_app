package com.jianghw.app.refresh.api;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.jianghw.app.refresh.constant.SpinnerStyle;
import com.jianghw.app.refresh.listener.OnStateChangedListenerable;

/**
 * 刷新内部组件
 */

public interface RefreshInternalable extends OnStateChangedListenerable {
    @NonNull
    View layView();

    @NonNull
    SpinnerStyle spinnferStyle();

    void primaryColor(@ColorInt int... colors);

    void initialized(@NonNull RefreshKernelable kernelable, int height, int extendHeight);

    void pulling(float percent, int offset, int height, int extendHeight);

    void releasing(float percent, int offset, int height, int extendHeight);

    void released(RefreshLayoutable layoutable, int height, int extendHeight);

    void startAnimator(@NonNull RefreshLayoutable layoutable, int height, int extendHeight);

    void stopAnimator(@NonNull RefreshLayoutable layoutable, int height, int extendHeight);

    void horizontalDrag(float percentX, int offsetX, int offsetMax);

    boolean isSupportHorizontalDrag();
}
