package com.example.btl_android_apporderticket.handle;

import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_android_apporderticket.model.Photo;

import java.util.List;

public class SlideRunnable implements Runnable {
    private ViewPager2 viewPagerTop;
    private List<Photo> listPhoto;
    private boolean reverse = false;

    public SlideRunnable(ViewPager2 viewPagerTop, List<Photo> listPhoto) {
        this.viewPagerTop = viewPagerTop;
        this.listPhoto = listPhoto;
    }

    @Override
    public void run() {
        int currentPos = viewPagerTop.getCurrentItem();
        if (currentPos == listPhoto.size() - 1) {
            reverse = true;
        } else if (currentPos == 0) {
            reverse = false;
        }
        if (reverse) {
            viewPagerTop.setCurrentItem(currentPos - 1);
        } else {
            viewPagerTop.setCurrentItem(currentPos + 1);
        }
    }
}
