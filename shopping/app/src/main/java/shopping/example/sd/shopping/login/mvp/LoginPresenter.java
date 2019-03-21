package shopping.example.sd.shopping.login.mvp;

import android.content.Context;

import shopping.example.sd.shopping.login.RegisterActivity;
import shopping.example.sd.shopping.login.bean.LoginBean;
import shopping.example.sd.shopping.login.bean.RegsinBean;

public class LoginPresenter {
    LoginModel loginModel;ImpView impView;

    public LoginPresenter(ImpView impView) {
        loginModel=new LoginModel();
        this.impView = impView;
    }

    public interface getLoginPresenter{
        void onLoginPresenter(LoginBean.ResultBean loginBean);
    }
    getLoginPresenter loginPresenter;

    public void setLoginPresenter(getLoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }
    public void getLoginPresenter(String phone,String pwd){
        loginModel.getLoginModel(phone,pwd);
        loginModel.setLoginModel(new LoginModel.getLoginModel() {
            @Override
            public void onLoginModel(LoginBean.ResultBean loginBean) {
                impView.getLoginView(loginBean);
            }
        });
    }

    public interface getRegsinPresenter{
        void onLoginPresenter(RegsinBean regsinBean);
    }
    getRegsinPresenter regsinPresenter;

    public void setRegsinPresenter(getRegsinPresenter regsinPresenter) {
        this.regsinPresenter = regsinPresenter;
    }

    public void getRegsinPresenter(String phone,String pwd){
        loginModel.getRegsinModel(phone,pwd);
        loginModel.setRegsinModel(new LoginModel.getRegsinModel() {
            @Override
            public void onRegsinModel(RegsinBean regsinBean) {
                impView.getRegsinView(regsinBean);
            }
        });
    }
}
