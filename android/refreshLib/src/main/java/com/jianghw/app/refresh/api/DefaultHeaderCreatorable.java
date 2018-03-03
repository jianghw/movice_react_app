package com.jianghw.app.refresh.api;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 默认Header创建器
 */

public interface DefaultHeaderCreatorable {

    RefreshHeaderable createRefreshHeader(@NonNull Context context, @NonNull RefreshLayoutable layoutable);
}
