package shopping.example.sd.shopping.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    //单列
    private static RetrofitUtils instance;
    private  RetrofitUtils(){}
    public static RetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RetrofitUtils.class){
                if(instance==null){
                    instance=new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(String url){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return RetrofitUtils.retrofit;
    }
    //封装ok加拦截器
    private static OkHttpClient okHttpClient;
    private static synchronized OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        return okHttpClient;
    }



    public <T> T doPost(String url,Class<T> apiService){
        Retrofit retrofit = getRetrofit(url);
        T t = retrofit.create(apiService);
        return t;
    }

}
