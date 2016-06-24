package com.sai.ui.animation;


import android.view.View;

public interface ViewAnimator<V extends View> {

    public void animateIn(V v);

    public void animateOut(V v);

}
