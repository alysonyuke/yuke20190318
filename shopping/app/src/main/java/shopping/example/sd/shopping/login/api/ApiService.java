package shopping.example.sd.shopping.login.api;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import shopping.example.sd.shopping.login.bean.LoginBean;
import shopping.example.sd.shopping.login.bean.RegsinBean;

public interface ApiService {
    @POST("v1/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLoginUrl(@Field("phone") String phone,@Field("pwd") String pwd);
    @POST("v1/register")
    @FormUrlEncoded
    Flowable<RegsinBean> getRegsinUrl(@Field("phone") String phone, @Field("pwd") String pwd);
}
