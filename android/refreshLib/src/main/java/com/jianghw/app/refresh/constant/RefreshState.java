package com.jianghw.app.refresh.constant;

/**
 * 刷新状态
 */

public enum RefreshState {
    None(0, false, false, false),
    PullDownToRefresh(1, true, false, false),//拖动
    PullDownCanceled(1, false, false, false),
    ReleaseToRefresh(1, true, false, false),//释放
    ReleaseToTwoLevel(1, true, false, false);


    public final boolean isHeader;
    private final boolean isFooter;
    /**
     * 正在拖动状态
     */
    private final boolean isDragging;
    /**
     * 正在刷新状态
     */
    private final boolean isOpening;
    /**
     * 正在完成状态
     */
    private final boolean isFinishing;

    RefreshState(int role, boolean dragging, boolean opening, boolean finishing) {
        this.isHeader = role == 1;
        this.isFooter = role == 2;
        this.isDragging = dragging;
        this.isOpening = opening;
        this.isFinishing = finishing;
    }
}
