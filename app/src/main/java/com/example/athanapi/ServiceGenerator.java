package com.example.athanapi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String BaseURL="http://api.aladhan.com/v1/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BaseURL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor).build();
        return client;
    }

    private  static  Retrofit retrofit=builder.build();
    public static <S> S CreateService(Class<S> ServiceClass){
        return retrofit.create(ServiceClass);
    }

}
