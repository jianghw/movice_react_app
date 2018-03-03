package com.jianghw.app.refresh.constant;

/**
 * 尺寸值的定义状态，用于在值覆盖的时候决定优先级
 * 越往下优先级越高
 */

public enum DimensionStatus {
    DefaultUnNotify(false),//默认值，但是还没通知确认
    Default(true),//默认值
    XmlWrapUnNotify(false),//Xml计算，但是还没通知确认
    XmlWrap(true),
    XmlExactUnNotify(false),//Xml 的view 指定，但是还没通知确认
    XmlExact(true),
    XmlLayoutUnNotify(false),//Xml 的layout 中指定，但是还没通知确认
    XmlLayout(true),
    CodeExactUnNotify(false),//代码指定，但是还没通知确认
    CodeExact(true),
    DeadLockUnNotify(false),//锁死，但是还没通知确认
    DeadLock(true);

    private final boolean notified;

    DimensionStatus(boolean notified) {
        this.notified = notified;
    }

    /**
     * 转换为未通知状态
     *
     * @return 未通知状态
     */
    public DimensionStatus unNotify() {
        if (notified) {
            DimensionStatus dimensionStatus = values()[ordinal() - 1];
            if (!dimensionStatus.notified) return dimensionStatus;
            return DefaultUnNotify;
        }
        return this;
    }

    /**
     * 转换为通知状态
     *
     * @return 通知状态
     */
    public DimensionStatus notified() {
        if (!notified) return values()[ordinal() + 1];
        return this;
    }

    /**
     * 是否可以被新的状态替换
     *
     * @param dimensionStatus 新转台
     * @return 小于等于
     */
    public boolean canReplaceWith(DimensionStatus dimensionStatus) {
        return this.ordinal() < dimensionStatus.ordinal() ||
                ((!notified || CodeExact == this) && this.ordinal() == dimensionStatus.ordinal());
    }

    /**
     * 是否没有达到新的状态
     *
     * @param status 新转台
     * @return 大于等于
     */
    public boolean getReplaceWith(DimensionStatus status) {
        return this.ordinal() >= status.ordinal();
    }
}
