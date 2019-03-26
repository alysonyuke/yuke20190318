package shopping.example.sd.shopping.login.mvp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import shopping.example.sd.shopping.api.API;
import shopping.example.sd.shopping.api.ApiService;
import shopping.example.sd.shopping.login.bean.LoginBean;
import shopping.example.sd.shopping.login.bean.RegsinBean;
import shopping.example.sd.shopping.utils.RetrofitUtils;

public class LoginModel {
    public interface getLoginModel{
        void onLoginModel(LoginBean.ResultBean loginBean);
    }
    getLoginModel loginModel;

    public void setLoginModel(getLoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void getLoginModel(String phone,String pwd){
        ApiService apiService = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
        apiService.getLoginUrl(phone,pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        LoginBean.ResultBean result = loginBean.getResult();
                        if (loginModel!=null){
                            loginModel.onLoginModel(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //注册的接口回调
    public interface getRegsinModel{
        void onRegsinModel(RegsinBean regsinBean);
    }
    getRegsinModel regsinModel;

    public void setRegsinModel(getRegsinModel regsinModel) {
        this.regsinModel = regsinModel;
    }
    public void getRegsinModel(String phone,String pwd){
        ApiService apiService = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
        apiService.getRegsinUrl(phone,pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RegsinBean>() {
                    @Override
                    public void onNext(RegsinBean regsinBean) {
                        if (regsinModel!=null){
                            regsinModel.onRegsinModel(regsinBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
