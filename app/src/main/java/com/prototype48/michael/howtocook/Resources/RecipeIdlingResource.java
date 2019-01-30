package com.prototype48.michael.howtocook.Resources;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import com.bumptech.glide.request.ResourceCallback;
import java.util.concurrent.atomic.AtomicBoolean;

public class RecipeIdlingResource  implements IdlingResource {
//
    @Nullable
    private volatile ResourceCallback mCallback;

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }



    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}