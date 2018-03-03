package com.jianghw.app.refresh.api;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 刷新内容组件
 */

public interface RefreshContentable {
    void moveSpinner(int spinner);

    boolean canRefresh();

    boolean canLoadMore();

    int measuredWidth();

    int measureHeight();

    void measure(int widthSpec, int heightSpec);

    void layout(int left, int top, int right, int bottom);

    View layView();

    View scrollableView();

    ViewGroup.LayoutParams layouParams();

    void actionDown(MotionEvent event);

    void actionUpOrCancel();

    void fling(int velocity);

    void setUpComponent(RefreshKernelable kernelable, View fixHeader, View fixedFooter);

    void initHeaderAndFooter(int headerHeight, int footerHeight);

    void scrollBoundaryDecider(ScrollBoundaryDeciderable boundaryDecider);

    void enableLoadMoreWhenContentNotFull(boolean enable);

}
