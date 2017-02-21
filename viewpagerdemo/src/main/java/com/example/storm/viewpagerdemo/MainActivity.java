package com.example.storm.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private int[] imgs = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c};
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter();
        viewPager.setAdapter(adapter);
      //  viewPager.setCurrentItem(adapter.getStartPageIndex());
        //设置动画
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                alphaPageTransform(page, position);
                pageTransform(page, position);
            }
        });
    }


    public void pageTransform(View view, float position) {
        if (position < -1) { // [-Infinity,-1)
            view.setRotation(mMaxRotate * -1);
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());

        } else if (position <= 1) { // [-1,1]
            if (position < 0)//[0，-1]
            {
                view.setPivotX(view.getWidth() * (0.5f + 0.5f * (-position)));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            } else//[1,0]
            {
                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotation(mMaxRotate);
            view.setPivotX(view.getWidth() * 0);
            view.setPivotY(view.getHeight());
        }
    }

    public void alphaPageTransform(View view, float position) {
        if (position < -1) {
            view.setAlpha(mMinAlpha);
        } else if (position <= 1) { // [-1,1]
            if (position < 0) //[0，-1]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                view.setAlpha(factor);
            } else//[1，0]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                view.setAlpha(factor);
            }
        } else { // (1,+Infinity]
            view.setAlpha(mMinAlpha);
        }
    }

    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
          //  container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView imageView = new ImageView(MainActivity.this);
//            imageView.setImageResource(imgs[position]);
//            container.addView(imageView);
//            return imageView;
            //对ViewPager页号求模取出View列表中要显示的项
            position %= imgs.length;
            if (position<0){
                position = imgs.length+position;
            }
            ImageView view = new ImageView(MainActivity.this);
            view.setImageResource(imgs[position]);
            //如果View已经在之前添加到了一个父组件，则必须先remove，
            // 否则会抛出IllegalStateException。
            ViewParent vp =view.getParent();
            if (vp!=null){
                ViewGroup parent = (ViewGroup)vp;
                parent.removeView(view);
            }
            view.setImageResource(imgs[position]);
            container.addView(view);
            //add listeners here if necessary
            return view;

        }

        public int getStartPageIndex() {
            int index = getCount() / 2;
            int remainder = index % imgs.length;
            index = index - remainder;
            return index;
        }
    }
}
