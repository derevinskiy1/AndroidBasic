package com.example.storm.sensortest.ReViewtrofit.service;

import com.example.storm.sensortest.ReViewtrofit.response.BookResponseService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

//https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3

//Get方法请求参数都会以key=value的方式拼接在url后面，
// Retrofit提供了两种方式设置请求参数。
// 第一种就是像上文提到的直接在interface中添加@Query注解，
// 还有一种方式是通过Interceptor实现，直接看如何通过Interceptor实现请求参数的添加。

public interface BookService {

    //如果请求的参数比较多,放到Map集合中....
    @GET("book/search")
    Call<BookResponseService>

    getSearchMapBooks(@QueryMap Map<String, String> options);


    //如果请求的相对地址也是需要调用方传递，那么可以使用@Path注解，示例代码如下：
    //https://api.douban.com/v2/book/1003078
    //@Path可以用于任何请求方式，包括Post，Put，Delete等等
    @GET("book/{id}")
    Call<BookResponseService> getPathBook(@Path("id") String id);


    //Post请求需要把请求参数放置在请求体中，而非拼接在url后面，先来看一个简单的例子
    @FormUrlEncoded
    @POST("book/reviews")
    Call<String> addReviews(@Field("book") String bookId, @Field("title") String title,
                            @Field("content") String content, @Field("rating") String rating);

    @GET("book/search")
    Call<BookResponseService> getSearchBooks(@Query("q") String name,
                                             @Query("tag") String tag,
                                             @Query("start") int start,
                                             @Query("count") int count);
//
//    @POST("book/search")
//    Call<BookResponseService> getPostBook(@Field("q") String name,
//                                          @Field("tag") String tag,
//                                          @Field("start") int start,
//                                          @Field("count") int count);

}
