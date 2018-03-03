package com.jianghw.app.refresh.util;

/**
 * 事件
 */

public class DelayedRunnable implements Runnable {

    private long delayMillis;
    private Runnable runnable;

    public DelayedRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public DelayedRunnable(Runnable runnable, long delayMillis) {
        this.runnable = runnable;
        this.delayMillis = delayMillis;
    }

    public long delayMillis() {
        return delayMillis;
    }

    @Override
    public void run() {
        try {
            if (runnable != null) {
                runnable.run();
                runnable = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
