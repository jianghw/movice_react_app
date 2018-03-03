package com.jianghw.app.refresh.attrs;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.jianghw.app.refresh.R;
import com.jianghw.app.refresh.constant.DimensionStatus;
import com.jianghw.app.refresh.util.DensityUtil;

/**
 * 自定义属性功能
 */

public class RefreshAttrs {

    protected float mHeaderMaxDragRate = 2.5f;  //最大拖动比率(最大高度/Header高度)
    protected float mFooterMaxDragRate = 2.5f;  //最大拖动比率(最大高度/Footer高度)
    protected float mHeaderTriggerRate = 1.0f;  //触发刷新距离 与 HeaderHeight 的比率
    protected float mFooterTriggerRate = 1.0f;  //触发加载距离 与 FooterHeight 的比率
    //<editor-fold desc="滑动属性">
    protected float mDragRate = .5f;
    protected int mReboundDuration = 250;//回弹动画时长
    protected int mFixedHeaderViewId;//固定在头部的视图Id
    protected int mFixedFooterViewId;//固定在底部的视图Id
    /**
     * 高度
     */
    protected int mHeaderHeight;//头部高度
    protected int mFooterHeight;//底部高度
    protected DimensionStatus mHeaderHeightStatus = DimensionStatus.DefaultUnNotify;
    protected DimensionStatus mFooterHeightStatus = DimensionStatus.DefaultUnNotify;
    protected int mHeaderInsetStart;    // Header 起始位置便宜
    protected int mFooterInsetStart;    // Footer 起始位置便宜
    protected int mHeaderExtendHeight;  //扩展高度
    protected int mFooterExtendHeight;  //扩展高度

    //<editor-fold desc="功能属性">
    protected boolean mEnableRefresh = true;
    protected boolean mEnableLoadMore = false;
    protected boolean mDisableContentWhenRefresh = false;//是否开启在刷新时候禁止操作内容视图
    protected boolean mDisableContentWhenLoading = false;//是否开启在刷新时候禁止操作内容视图
    protected boolean mEnableHeaderTranslationContent = true;//是否启用内容视图拖动效果
    protected boolean mEnableFooterTranslationContent = true;//是否启用内容视图拖动效果
    protected boolean mEnablePreviewInEditMode = true;//是否在编辑模式下开启预览功能
    protected boolean mEnableAutoLoadMore = true;//是否在列表滚动到底部时自动加载更多
    protected boolean mEnableOverScrollBounce = true;//是否启用越界回弹
    protected boolean mEnablePureScrollMode = false;//是否开启纯滚动模式
    protected boolean mEnableScrollContentWhenLoaded = true;//是否在加载更多完成之后滚动内容显示新数据
    protected boolean mEnableScrollContentWhenRefreshed = true;//是否在刷新完成之后滚动内容显示新数据
    protected boolean mEnableLoadMoreWhenContentNotFull = true;//在内容不满一页的时候，是否可以上拉加载更多
    protected boolean mEnableFooterFollowWhenLoadFinished = false;//是否在全部加载结束之后Footer跟随内容 1.0.4-6
    protected boolean mEnableClipHeaderWhenFixedBehind = true;//当 Header FixedBehind 时候是否剪裁遮挡 Header
    protected boolean mEnableClipFooterWhenFixedBehind = true;//当 Footer FixedBehind 时候是否剪裁遮挡 Footer
    protected boolean mEnableOverScrollDrag = true;//是否启用越界拖动（仿苹果效果）1.0.4-6

    protected boolean mManualLoadMore = false;//是否手动设置过LoadMore，用于智能开启
    protected boolean mManualNestedScrolling = false;//是否手动设置过 NestedScrolling，用于智能开启
    protected boolean mManualHeaderTranslationContent = false;//是否手动设置过内容视图拖动效果

    protected int[] mPrimaryColors;//主题颜色

    public RefreshAttrs(Context context, AttributeSet attrs) {
        DensityUtil density = new DensityUtil();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmartRefreshLayout);

        mHeaderMaxDragRate = typedArray.getFloat(R.styleable.SmartRefreshLayout_srlHeaderMaxDragRate, mHeaderMaxDragRate);
        mFooterMaxDragRate = typedArray.getFloat(R.styleable.SmartRefreshLayout_srlFooterMaxDragRate, mFooterMaxDragRate);
        mHeaderTriggerRate = typedArray.getFloat(R.styleable.SmartRefreshLayout_srlHeaderTriggerRate, mHeaderTriggerRate);
        mFooterTriggerRate = typedArray.getFloat(R.styleable.SmartRefreshLayout_srlFooterTriggerRate, mFooterTriggerRate);

        mDragRate = typedArray.getFloat(R.styleable.SmartRefreshLayout_srlDragRate, mDragRate);
        mReboundDuration = typedArray.getInt(R.styleable.SmartRefreshLayout_srlReboundDuration, mReboundDuration);
        mFixedHeaderViewId = typedArray.getResourceId(R.styleable.SmartRefreshLayout_srlFixedHeaderViewId, View.NO_ID);
        mFixedFooterViewId = typedArray.getResourceId(R.styleable.SmartRefreshLayout_srlFixedFooterViewId, View.NO_ID);

        mHeaderHeight = typedArray.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderHeight, density.dip2px(100));
        mFooterHeight = typedArray.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterHeight, density.dip2px(60));
        mHeaderHeightStatus = typedArray.hasValue(R.styleable.SmartRefreshLayout_srlHeaderHeight) ? DimensionStatus.XmlLayoutUnNotify : mHeaderHeightStatus;
        mFooterHeightStatus = typedArray.hasValue(R.styleable.SmartRefreshLayout_srlFooterHeight) ? DimensionStatus.XmlLayoutUnNotify : mFooterHeightStatus;
        mHeaderInsetStart = typedArray.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderInsetStart, 0);
        mFooterInsetStart = typedArray.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterInsetStart, 0);
        mHeaderExtendHeight = (int) Math.max((mHeaderHeight * (mHeaderMaxDragRate - 1)), 0);
        mFooterExtendHeight = (int) Math.max((mFooterHeight * (mFooterMaxDragRate - 1)), 0);

        mEnableRefresh = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableRefresh, mEnableRefresh);
        mEnableLoadMore = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMore, mEnableLoadMore);
        mDisableContentWhenRefresh = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenRefresh, mDisableContentWhenRefresh);
        mDisableContentWhenLoading = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenLoading, mDisableContentWhenLoading);
        mEnableHeaderTranslationContent = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent, mEnableHeaderTranslationContent);
        mEnableFooterTranslationContent = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent, mEnableFooterTranslationContent);
        mEnablePreviewInEditMode = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePreviewInEditMode, mEnablePreviewInEditMode);
        mEnableAutoLoadMore = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableAutoLoadMore, mEnableAutoLoadMore);
        mEnableOverScrollBounce = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollBounce, mEnableOverScrollBounce);
        mEnablePureScrollMode = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePureScrollMode, mEnablePureScrollMode);
        mEnableScrollContentWhenLoaded = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenLoaded, mEnableScrollContentWhenLoaded);
        mEnableScrollContentWhenRefreshed = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenRefreshed, mEnableScrollContentWhenRefreshed);
        mEnableLoadMoreWhenContentNotFull = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMoreWhenContentNotFull, mEnableLoadMoreWhenContentNotFull);
        mEnableFooterFollowWhenLoadFinished = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenLoadFinished, mEnableFooterFollowWhenLoadFinished);
        mEnableClipHeaderWhenFixedBehind = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipHeaderWhenFixedBehind, mEnableClipHeaderWhenFixedBehind);
        mEnableClipFooterWhenFixedBehind = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipFooterWhenFixedBehind, mEnableClipFooterWhenFixedBehind);
        mEnableOverScrollDrag = typedArray.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag, mEnableOverScrollDrag);

        mManualLoadMore = typedArray.hasValue(R.styleable.SmartRefreshLayout_srlEnableLoadMore);
        mManualNestedScrolling = typedArray.hasValue(R.styleable.SmartRefreshLayout_srlEnableNestedScrolling);
        mManualHeaderTranslationContent = typedArray.hasValue(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent);

        int accentColor = typedArray.getColor(R.styleable.SmartRefreshLayout_srlAccentColor, 0);
        int primaryColor = typedArray.getColor(R.styleable.SmartRefreshLayout_srlPrimaryColor, 0);
        if (primaryColor != 0 && accentColor != 0) {
            mPrimaryColors = new int[]{primaryColor, accentColor};
        } else if (primaryColor != 0) {
            mPrimaryColors = new int[]{primaryColor};
        } else if (accentColor != 0) {
            mPrimaryColors = new int[]{0, accentColor};
        }

        typedArray.recycle();
    }
}
