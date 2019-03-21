package shopping.example.sd.shopping.login.mvp;

import shopping.example.sd.shopping.login.bean.LoginBean;
import shopping.example.sd.shopping.login.bean.RegsinBean;

public interface ImpView {
    void getLoginView(LoginBean.ResultBean loginBean);
    void getRegsinView(RegsinBean regsinBean);
}
