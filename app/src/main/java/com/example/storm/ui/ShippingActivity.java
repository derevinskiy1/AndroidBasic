package com.example.storm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShippingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_add;
    private TextView tv_count;
    private ImageView shopCart;//购物车
    private int buyNum;         //购买的数量
    private TextView tv_shop_count;  //购买的件数


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        initView();
    }

    //初始化控件
    private void initView() {
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_count = (TextView) findViewById(R.id.tv_count);
        shopCart = (ImageView) findViewById(R.id.shop_cart);
        tv_shop_count = (TextView) findViewById(R.id.tv_shop_count);
        iv_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
        v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
        ImageView redBall = new ImageView(this);
        redBall.setImageResource(R.mipmap.red);// 设置购买的图片
        setAnim(redBall, startLocation);// 开始执行动画
    }

    /**
     * 创建动画层
     *
     * @return animLayout
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    public void setAnim(final View v, int[] startLocation) {
        ViewGroup anim_layout = createAnimLayout();
        anim_layout.addView(v); //把动画小球添加到动画层final
        View view = addViewToAnimLayout(anim_layout, v, startLocation);
        int[] endLocation = new int[2]; //存储动画结束位置的X、Y坐标
        shopCart.getLocationInWindow(endLocation);  //shopCart购物车
        // 计算位移
        int endX = endLocation[0] - startLocation[0];  //动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];   //动画位移的y坐标
        Log.i("计算位移:", +endX + "," + endY);
        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0); //X轴平移动画
        translateAnimationX.setInterpolator(new LinearInterpolator());
        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);//Y轴平移动画
        translateAnimationY.setInterpolator(new AccelerateInterpolator()); //动画加速器
        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(1000);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束设置购买的图片为GONE
                v.setVisibility(View.GONE);
                buyNum++;//让购买数量加1
                tv_count.setText(buyNum + "");
                tv_shop_count.setVisibility(View.VISIBLE);
                tv_shop_count.setText(buyNum + "");
            }
        });
    }
}
