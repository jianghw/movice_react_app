package com.jianghw.app.refresh.impl;

import android.view.View;

import com.jianghw.app.refresh.api.RefreshHeaderable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jianghw on 18-3-3.
 */

public class RefreshHeaderWrapperImpl extends RefreshInternalWrapperImpl
        implements RefreshHeaderable, InvocationHandler {

    public RefreshHeaderWrapperImpl(View childAt) {
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
