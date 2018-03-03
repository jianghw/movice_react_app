package com.jianghw.app.refresh.impl;

import android.support.annotation.NonNull;
import android.view.View;

import com.jianghw.app.refresh.api.RefreshInternalable;
import com.jianghw.app.refresh.api.RefreshKernelable;
import com.jianghw.app.refresh.api.RefreshLayoutable;
import com.jianghw.app.refresh.constant.RefreshState;
import com.jianghw.app.refresh.constant.SpinnerStyle;

/**
 * 组件底部包装
 */

public class RefreshInternalWrapperImpl implements RefreshInternalable {
    @Override
    public void onStateChanged(@NonNull RefreshLayoutable layoutable, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }

    @NonNull
    @Override
    public View layView() {
        return null;
    }

    @NonNull
    @Override
    public SpinnerStyle spinnferStyle() {
        return null;
    }

    @Override
    public void primaryColor(int... colors) {

    }

    @Override
    public void initialized(@NonNull RefreshKernelable kernelable, int height, int extendHeight) {

    }

    @Override
    public void pulling(float percent, int offset, int height, int extendHeight) {

    }

    @Override
    public void releasing(float percent, int offset, int height, int extendHeight) {

    }

    @Override
    public void released(RefreshLayoutable layoutable, int height, int extendHeight) {

    }

    @Override
    public void startAnimator(@NonNull RefreshLayoutable layoutable, int height, int extendHeight) {

    }

    @Override
    public void stopAnimator(@NonNull RefreshLayoutable layoutable, int height, int extendHeight) {

    }

    @Override
    public void horizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
}
