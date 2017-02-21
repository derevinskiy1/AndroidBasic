package com.example.storm.sensortest.ReViewtrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.storm.sensortest.R;
import com.example.storm.sensortest.ReViewtrofit.response.BookResponseService;
import com.example.storm.sensortest.ReViewtrofit.service.BookService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReViewRetrofitActivity extends AppCompatActivity {
    private String url = "https://api.douban.com/v2/";
    public static final String TAG = ReViewRetrofitActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_view_retrofit);
        getBook();
        getCustomBook();
        //   getPostBook();
    }

    private void getBook() {
        //创建一个Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService service = retrofit.create(BookService.class);
        Call<BookResponseService> call = service.getSearchBooks("小王子", "", 0, 3);
        //同步请求
        //BookResponseService response = call.execute().body();
        //异步请求
        call.enqueue(new Callback<BookResponseService>() {
            @Override
            public void onResponse(Call<BookResponseService> call, Response<BookResponseService> response) {
                Log.i(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<BookResponseService> call, Throwable t) {
            }
        });
    }


    private void getCustomBook() {
        //创建一个Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService service = retrofit.create(BookService.class);
        Map<String, String> map = new HashMap<>();
        map.put("q", "小王子");
        map.put("tag", "");
        map.put("start", "0");
        map.put("count", "3");
        Call<BookResponseService> call = service.getSearchMapBooks(map);
        //异步请求
        call.enqueue(new Callback<BookResponseService>() {
            @Override
            public void onResponse(Call<BookResponseService> call, Response<BookResponseService> response) {
                Log.i(TAG, "onResponse: " + response.body().getTotal());
            }

            @Override
            public void onFailure(Call<BookResponseService> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());

            }
        });

    }

    private void getPostBook() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        BookService bookService = retrofit.create(BookService.class);
//        Call<BookResponseService> call = bookService.getPostBook("小王子", "", 0, 3);
//        call.enqueue(new Callback<BookResponseService>() {
//            @Override
//            public void onResponse(Call<BookResponseService> call, Response<BookResponseService> response) {
//                Log.i(TAG, "onResponse: " + response.body().getCount());
//            }
//
//            @Override
//            public void onFailure(Call<BookResponseService> call, Throwable t) {
//                Log.i(TAG, "Post请求onFailure: " + t.getMessage());
//            }
//        });
    }

//    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
//
//    @NonNull
//    private RequestBody createPartFromString(String descriptionString) {
//        return RequestBody.create(
//                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
//    }
//
//    @NonNull
//    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
//        File file = FileUtils.getFile(this, fileUri);
//
//        // 为file建立RequestBody实例
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
//
//        // MultipartBody.Part借助文件名完成最终的上传
//        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
//    }
//
//    private void upload() {
//        Uri file1Uri = "" // 从文件选择器或者摄像头中获取
//        Uri file2Uri = ""
//
////        Retrofit ServiceGenerator = new Retrofit.Builder()
////                .baseUrl(url)
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
//// 创建上传的service实例
//        FileUploadService service =
//                ServiceGenerator.createService(FileUploadService.class);
//
//// 创建文件的part (photo, video, ...)
//        MultipartBody.Part body1 = prepareFilePart("video", file1Uri);
//        MultipartBody.Part body2 = prepareFilePart("thumbnail", file2Uri);
//
//// 添加其他的part
//        RequestBody description = createPartFromString("hello, this is description speaking");
//
//// 最后执行异步请求操作
//        Call<ResponseBody> call = service.uploadMultipleFiles(description, body1, body2);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call,
//                                   Response<ResponseBody> response) {
//                Log.v("Upload", "success");
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("Upload error:", t.getMessage());
//            }
//        });
//    }

}
