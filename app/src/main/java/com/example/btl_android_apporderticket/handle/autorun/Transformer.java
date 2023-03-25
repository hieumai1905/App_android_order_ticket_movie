package com.example.btl_android_apporderticket.handle.autorun;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

public class Transformer {
    public static CompositePageTransformer compositePageTransformer;

    public static CompositePageTransformer getTransformer(int margin) {
        compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(margin));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.9f + r * 0.1f);
            }
        });
        return compositePageTransformer;
    }
}
