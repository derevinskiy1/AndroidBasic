package com.example.storm.pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.btn1_pay)
    Button btn1Pay;
    @InjectView(R.id.btn2_pay)
    Button btn2Pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1_pay, R.id.btn2_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1_pay:
                zfb();
                break;
            case R.id.btn2_pay:
                break;
        }
    }

    /**
     1. 拼接支付信息,post到服务器;-->request
     1. 支付信息包含支付方式
     2. 服务器:是我们自己的服务器
     3. 支付协议:`http://mobileif.maizuo.com/version3/orderform/order?version=2`
     2. 服务器返回`支付串码`;--->reponse

     3. 拿着支付串码,调用第三方服务,完成支付-->5分钟
     4. 处理支付结果
     1. 同步返回:支付后通知我们自己的apk
     2. 异步通知:支付后通知我们的server
     */

    /**
     * 支付宝支付
     */
    private void zfb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost post = new HttpPost("http://mobileif.maizuo.com/version3/orderform/order?version=2");
                    post.addHeader("Content-Type", "application/json");
                    // 发送拼接好的 支付信息
                    post.setEntity(new StringEntity(
                            "{\"goodInfos\":[{\"goodCounts\":\"1\",\"goodExtInfo\":{},\"goodIDs\":\"361\",\"goodType\":\"1\"}],\"loginFlag\":\"0\",\"mobile\":\"18682036558\",\"orderId\":\"0\",\"otherInfo\":{\"agentID\":\"0-maizuo\",\"channelID\":\"31\",\"clientID\":\"31\"},\"payDatas\":{\"discountInfo\":{\"activeID\":\"0\",\"discountID\":\"0\",\"discountPrice\":\"\"},\"payInfo\":[{\"bankType\":\"7\",\"payCount\":\"3200\",\"payTicketCount\":\"1\",\"payType\":\"1\"}],\"payPass\":\"\",\"returnUrl\":\"\",\"totalPrice\":\"3200\"},\"processPath\":\"1\",\"sessionKey\":\"mqneaadqapkpkqshxvdj\",\"userID\":\"200394160\"}"));
                    // 发起请求
                    HttpResponse response = httpClient.execute(post);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        // 得到响应结果
                        String result = EntityUtils.toString(entity);
                        // json解析拿到支付串码
                        JSONObject rootJsonObject = new JSONObject(result);
                        JSONObject payExtInfoJsonObject = rootJsonObject.getJSONObject("payExtInfo");
                        // 支付串码,第三步调用支付平台核心支付方法需要的参数
                        String payInfo = payExtInfoJsonObject.optString("alipayVerifyKey");
                        System.out.println("payInfo:" + payInfo);

                        // 3. 拿着支付串码,调用第三方服务,完成支付-->5分钟
                        // 获取Alipay对象，构造参数为当前Activity和Handler实例对象
                        AliPay alipay = new AliPay(MainActivity.this, mHandler);
                        // 调用pay方法，将订单信息传入
                        String payResult = alipay.pay(payInfo);
//                        // 处理返回结果
                        System.out.println("payResult: " + payResult);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
