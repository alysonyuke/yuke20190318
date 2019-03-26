package shopping.example.sd.shopping.api;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import shopping.example.sd.shopping.bean.DstailsBean;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;
import shopping.example.sd.shopping.login.bean.LoginBean;
import shopping.example.sd.shopping.login.bean.RegsinBean;

public interface ApiService {
    //登陆
    @POST("small/user/v1/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLoginUrl(@Field("phone") String phone,@Field("pwd") String pwd);
    //注册
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Flowable<RegsinBean> getRegsinUrl(@Field("phone") String phone, @Field("pwd") String pwd);
    //banner轮播图
    @GET("small/commodity/v1/bannerShow")
    Flowable<ShowBannerBean> getBannerUrl();
    //首页数据
    @GET("small/commodity/v1/commodityList")
    Flowable<ShowDataBean> getShowDataUrl();
    //点击搜素
    @GET("small/commodity/v1/findCommodityByKeyword")
    Flowable<SearchBean>getSearchUrl(@Query("keyword") String keyword,@Query("page") int page,@Query("count") int count);
    @GET(API.DetailsUrl)
    Flowable<DstailsBean> getDetailsUrl(@Query("commodityId") int id);
}
