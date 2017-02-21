package com.example.storm.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.storm.ui.fragment.FirstFragment;
import com.example.storm.ui.fragment.SecondFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SlidingPanActivity extends AppCompatActivity implements android.support.v4.widget.SlidingPaneLayout.PanelSlideListener, View.OnClickListener {

    @InjectView(R.id.main_menu)
    FrameLayout mainMenu;
    @InjectView(R.id.main_content)
    FrameLayout mainContent;
    @InjectView(R.id.SlidingPaneLayout)
    android.support.v4.widget.SlidingPaneLayout SlidingPaneLayout;
    private TextView one;
    private TextView two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_pan);
        ButterKnife.inject(this);
        addFragment();

        SlidingPaneLayout.setPanelSlideListener(this);
        //  setCoveredFadeColor
        // 设置ViewLeft的衰变颜色。可以理解为从open状态到close状态时候渐变的颜色，从自身的颜色渐变到衰变颜色。

        //   setSliderFadeColor
        //  设置ViewContent的衰变颜色。可以理解为从close状态到open状态时候渐变的颜色，从自身的颜色渐变到衰变颜色。

        SlidingPaneLayout.setCoveredFadeColor(Color.RED);
        SlidingPaneLayout.setSliderFadeColor(Color.RED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_menu, new MenuFragment()).commit();

    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
//        mainMenu.setScaleX(slideOffset / 2 + 0.5F);
//        mainMenu.setScaleY(slideOffset / 2 + 0.5F);
//        mainContent.setScaleY(1 - slideOffset / 5);
    }

    @Override
    public void onPanelOpened(View panel) {

    }

    @Override
    public void onPanelClosed(View panel) {

    }

    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    Fragment fragment;

    //如何切换 fragement,不重新实例化?????
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                switchContent(FirstFragment.newInstance());
                break;
            case R.id.two:
                switchContent(SecondFragment.newInstance());
                break;
        }
    }
    Fragment from = new ContentFragment();

    Fragment mContent;

    private void switchFragment(Fragment from,Fragment to){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            if (!to.isAdded()) {	// 先判断是否被add过
                transaction.hide(from).add(R.id.main_content, to).addToBackStack(null).commit(); // 隐藏当前的fragment，add下一个到Activity中
                System.out.println("-----added");
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }



    //    正确的切换方式是 add()，切换时 hide()，add()另一个 Fragment；再次切换时，只需 hide()当前，
//    show()另一个。
    /** 修改显示的内容 不会重新加载 **/
    public void switchContent(Fragment to) {
        if (from != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from).add(R.id.main_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
                System.out.println("--added");
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
                System.out.println("--hide");
            }
            from = to;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
