package com.jianghw.app.refresh.impl;

import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

import com.jianghw.app.refresh.api.RefreshContentable;
import com.jianghw.app.refresh.api.RefreshKernelable;

/**
 * 刷新内容包装
 */

public class RefreshContentWrapperImpl implements RefreshContentable {

    private View mRealContentView;//被包裹的原真实视图
    private View mContentView;//直接内容视图


    public RefreshContentWrapperImpl(View view) {
        this.mContentView = mRealContentView = view;
    }

    /**
     * 是可滑动的控件
     *
     * @param view
     * @return
     */
    public static boolean isScrollableView(View view) {
        return view instanceof AbsListView
                || view instanceof ScrollView
                || view instanceof ScrollingView
                || view instanceof NestedScrollingChild
                || view instanceof NestedScrollingParent
                || view instanceof WebView
                || view instanceof ViewPager;
    }

    @Override
    public void moveSpinner(int spinner) {

    }

    @Override
    public boolean canRefresh() {
        return false;
    }

    @Override
    public boolean canLoadMore() {
        return false;
    }

    @Override
    public int measuredWidth() {
        return 0;
    }

    @Override
    public int measureHeight() {
        return 0;
    }

    @Override
    public void measure(int widthSpec, int heightSpec) {

    }

    @Override
    public void layout(int left, int top, int right, int bottom) {

    }

    @Override
    public View layView() {
        return null;
    }

    @Override
    public View scrollableView() {
        return null;
    }

    @Override
    public ViewGroup.LayoutParams layouParams() {
        return null;
    }

    @Override
    public void actionDown(MotionEvent event) {

    }

    @Override
    public void actionUpOrCancel() {

    }

    @Override
    public void fling(int velocity) {

    }

    @Override
    public void setUpComponent(RefreshKernelable kernelable, View fixHeader, View fixedFooter) {

    }

    @Override
    public void initHeaderAndFooter(int headerHeight, int footerHeight) {

    }
}
