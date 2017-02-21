package com.example.storm.sensortest.ReViewtrofit.utils;

/**
 * Created by zhiwenyan on 12/20/16.
 */

public class HttpResult<T> {
    private int requestCode;
    private String resultMessage;
    private T data;


    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "requestCode=" + requestCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
