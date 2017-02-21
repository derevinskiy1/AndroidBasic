package com.example.storm.testglide;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;


public class MyViewTarget extends ViewTarget<MyFrameLayout, GlideDrawable> {
    public MyViewTarget(MyFrameLayout view) {
        super(view);

    }

    @Override
    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
        this.view.setImage(resource.getCurrent());
    }
}

