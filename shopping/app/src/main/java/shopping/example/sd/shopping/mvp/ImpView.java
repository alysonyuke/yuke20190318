package shopping.example.sd.shopping.mvp;

import java.util.List;

import shopping.example.sd.shopping.bean.DstailsBean;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShopBean;
import shopping.example.sd.shopping.bean.ShopSearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;

public interface ImpView {
    void getBannerView(List<ShowBannerBean.ResultBean> result);
    void getDataView(ShowDataBean showDataBean);
    void getSearchView(List<SearchBean.ResultBean> searchbean);
    void getDetailsView(DstailsBean dstailsBean);
    void getShopView(ShopBean shopBean);
    void getSSView(ShopSearchBean searchBean);
}
