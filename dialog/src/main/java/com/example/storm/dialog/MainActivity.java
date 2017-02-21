package com.example.storm.dialog;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.btn4)
    Button btn4;
    @InjectView(R.id.btn5)
    Button mBtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn5:
                showPopupWindow();
                break;
            case R.id.btn1:
                //第二个参数表示弹出对话框的类型
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        //标题
                        .setTitleText("支付")
                        //内容
                        .setContentText("支付成功！")
                        .show();
                break;
            case R.id.btn2:
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        //内容
                        .setContentText("确定要删除吗？")
                        //确定按钮的文本
                        .setConfirmText("是的,删除")
                        //取消按钮的文本
                        .setCancelText("不删除")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
                break;
            case R.id.btn3:
                //先弹出提示对话框.在弹出成功对话框
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText("确定要删除吗？")
                        .setConfirmText("是的,删除")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.setTitleText("删除")
                                        .setContentText("删除成功！")
                                        .setConfirmText("确定")
                                        .setConfirmClickListener(null)
                                        //改变对话框的类型
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        }).show();
                break;
        }
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        PopupWindow pw = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 产生背景变暗效果
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = 0.5f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(lp);
        pw.setTouchable(true);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());  //点击外面消失需要设置这个
        pw.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);

        pw.update();

        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = MainActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                MainActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                MainActivity.this.getWindow().setAttributes(lp);
            }
        });

    }

    @OnClick(R.id.btn4)
    public void onClick() {
        customDialog();
    }

    private void customDialog() {
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
//        MyDialog dialog = new MyDialog(this, view, R.style.load_dialog);
//        dialog.show();
        dialog d = new dialog();
        d.show(getSupportFragmentManager(), "");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
