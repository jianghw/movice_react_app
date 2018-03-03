package com.jianghw.app.refresh.attrs;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

import com.jianghw.app.refresh.api.DefaultHeaderCreatorable;
import com.jianghw.app.refresh.api.RefreshContentable;
import com.jianghw.app.refresh.api.RefreshFooterable;
import com.jianghw.app.refresh.api.RefreshHeaderable;
import com.jianghw.app.refresh.api.RefreshInternalable;
import com.jianghw.app.refresh.api.RefreshLayoutable;
import com.jianghw.app.refresh.api.ScrollBoundaryDeciderable;
import com.jianghw.app.refresh.constant.SpinnerStyle;
import com.jianghw.app.refresh.impl.RefreshContentWrapperImpl;
import com.jianghw.app.refresh.impl.RefreshFooterWrapperImpl;
import com.jianghw.app.refresh.impl.RefreshHeaderWrapperImpl;
import com.jianghw.app.refresh.util.DelayedRunnable;
import com.jianghw.app.refresh.util.DensityUtil;

import java.util.List;

/**
 * 可刷新功能布局
 */

public class SmartRefreshLayout extends ViewGroup implements RefreshLayoutable,
        NestedScrollingParent, NestedScrollingChild {
    /**
     * 静态对象
     */
    private static DefaultHeaderCreatorable sHeaderCreator;
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
     * 控件属性对象
     */
    private RefreshAttrs mRefreshAttrs;
    /**
     * 视图布局控件接口类
     */
    private RefreshContentable mRefreshContent;
    private RefreshHeaderable mRefreshHeader;
    private RefreshFooterable mRefreshFooter;
    /**
     * 事件
     */
    private List<DelayedRunnable> mDelayedRunnables;
    private Handler mHandler;


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

        mRefreshAttrs = new RefreshAttrs(context, attrs);
    }

    //<editor-fold desc="生命周期 life cycle">

    /**
     * 自定义View中所有的子控件均被映射成xml后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final int count = getChildCount();
        if (count > 3) throw new RuntimeException("Most only support three sub view");

        int indexContent = -1;//内容布局的位置
        int[] indexArray = {1, 0, 2};//故意打乱顺序
        for (int index : indexArray) {
            if (index < count) {
                View childAt = getChildAt(index);
                //不是内部刷新组件时，即为内容布局控件
                if (!(childAt instanceof RefreshInternalable)) {
                    indexContent = index;
                }
                if (RefreshContentWrapperImpl.isScrollableView(childAt)) {
                    indexContent = index;
                    break;
                }
            }
        }
        int indexHeader = -1;
        int indexFooter = -1;
        if (indexContent >= 0) {
            mRefreshContent = new RefreshContentWrapperImpl(getChildAt(indexContent));
            //分析视图布局可能出现的情况
            if (indexContent == 1) {//内容中间时
                indexHeader = 0;
                if (count == 3) indexFooter = 2;
            } else if (count == 2) {//内容最上时
                indexFooter = 1;
            }
        }

        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            //header 位置可能为-1,0,1
            if (i == indexHeader || (i != indexFooter && indexHeader == -1 && mRefreshHeader == null && childAt instanceof RefreshHeaderable)) {
                mRefreshHeader = (childAt instanceof RefreshHeaderable) ? (RefreshHeaderable) childAt : new RefreshHeaderWrapperImpl(childAt);
            } else if (i == indexFooter || indexFooter == -1 && childAt instanceof RefreshFooterable) {
                //footer 位置可能为-1,1,2
                mRefreshFooter = (childAt instanceof RefreshFooterable) ? (RefreshFooterable) childAt : new RefreshFooterWrapperImpl(childAt);
            }
        }
        //使用isInEditMode解决可视化编辑器无法识别自定义控件的问题
        if (isInEditMode()) {
            if (mRefreshAttrs.mPrimaryColors != null) {
                //TODO
                //                if(mRefreshHeader!=null)mRefreshHeader.set
                //                if(mRefreshFooter!=null)mRefreshFooter.set
            }
            //显示布局在屏幕的最前方
            if (mRefreshContent != null && mRefreshHeader.spinnferStyle() != SpinnerStyle.FixedBehind) {
                bringChildToFront(mRefreshContent.layView());
            }
            if (mRefreshHeader != null && mRefreshFooter.spinnferStyle() != SpinnerStyle.FixedBehind) {
                bringChildToFront(mRefreshHeader.layView());
            }
        }
    }

    /**
     * 将view绑定到activity所在window时调用
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) return;

        if (mHandler == null) {
            mHandler = new Handler();
        }

        if (mDelayedRunnables != null) {
            for (DelayedRunnable runnable : mDelayedRunnables) {
                mHandler.postDelayed(runnable, runnable.delayMillis());
            }
            if (!mDelayedRunnables.isEmpty()) mDelayedRunnables.clear();
            mDelayedRunnables = null;
        }
        //加入头布局
        if (mRefreshHeader == null) {
            mRefreshHeader = sHeaderCreator.createRefreshHeader(getContext(), this);
            if (!(mRefreshHeader.layView().getLayoutParams() instanceof MarginLayoutParams)) {
                if (mRefreshHeader.spinnferStyle() == SpinnerStyle.Scale) {
                    addView(mRefreshHeader.layView(), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                } else {
                    addView(mRefreshHeader.layView(), LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                }
            }
        }
        //TODO 尾巴布局

        for (int i = 0, len = getChildCount(); mRefreshContent == null && i < len; i++) {
            View childAt = getChildAt(i);
            if (mRefreshContent == null ||
                    childAt != mRefreshHeader.layView() && (mRefreshFooter == null || childAt != mRefreshFooter.layView())) {
                mRefreshContent = new RefreshContentWrapperImpl(childAt);
            }
        }

        if (mRefreshContent == null) {
            int padding = DensityUtil.dp2px(20);
            TextView error = new TextView(getContext());
            error.setTextColor(0xffff6600);
            error.setGravity(Gravity.CENTER);
            error.setTextSize(20);
            error.setPadding(padding, padding, padding, padding);
            error.setText("懵逼啦~ 出错啦~");
            addView(error, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mRefreshContent = new RefreshContentWrapperImpl(error);
        }
        //固定的布局
        View fixedHeaderView = mRefreshAttrs.mFixedHeaderViewId > 0 ? findViewById(mRefreshAttrs.mFixedHeaderViewId) : null;
        View fixedFooterView = mRefreshAttrs.mFixedFooterViewId > 0 ? findViewById(mRefreshAttrs.mFixedFooterViewId) : null;

//        mRefreshContent.sc
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 判断view是否获取焦点，参数hasWindowFocus 对应返回true 和false 可以用来判断view是否进出后台
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultHeaderCreatorable creator) {
        sHeaderCreator = creator;
    }

    @Override
    public RefreshLayoutable setScrollBoundaryDecider(ScrollBoundaryDeciderable boundary) {
        mScrollBoundaryDecider = boundary;
        if (mRefreshContent != null) {
            mRefreshContent.scrollBoundaryDecider(boundary);
        }
        return this;
    }

}
