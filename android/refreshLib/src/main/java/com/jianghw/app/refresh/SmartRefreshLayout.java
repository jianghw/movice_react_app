package com.jianghw.app.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.jianghw.app.refresh.api.RefreshLayoutable;

/**
 * 可刷新功能布局
 */

public class SmartRefreshLayout extends ViewGroup implements RefreshLayoutable,
        NestedScrollingParent, NestedScrollingChild {
    /**
     * 专门用于处理滚动效果的工具类
     */
    private Scroller mScroller;
    /**
     * 用于设置最小加速率和最大速率
     */
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    /**
     * 在可滑动的控件中用于区别单击子控件和滑动操作的一个伐值
     */
    private int mTouchSlop;

    /**
     * extends ViewGroup ------------>
     */
    public SmartRefreshLayout(Context context) {
        super(context);
        this.initView(context, null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public SmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //控件的绘制区域是否在padding里面
        setClipToPadding(false);

        mScroller = new Scroller(context);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
        mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmartRefreshLayout);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }


}
