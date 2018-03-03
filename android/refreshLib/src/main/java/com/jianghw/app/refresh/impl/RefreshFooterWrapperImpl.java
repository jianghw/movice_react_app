package com.jianghw.app.refresh.impl;

import android.view.View;

import com.jianghw.app.refresh.api.RefreshFooterable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jianghw on 18-3-3.
 */

public class RefreshFooterWrapperImpl extends RefreshInternalWrapperImpl
        implements RefreshFooterable ,InvocationHandler{
    public RefreshFooterWrapperImpl(View childAt) {
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
