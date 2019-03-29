package shopping.example.sd.shopping.mvp;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import shopping.example.sd.shopping.api.API;
import shopping.example.sd.shopping.api.ApiService;
import shopping.example.sd.shopping.bean.DstailsBean;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShopBean;
import shopping.example.sd.shopping.bean.ShopSearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;
import shopping.example.sd.shopping.utils.RetrofitUtils;

public class ShowPresent {
    ImpView impView;ShowModel showModel;

    public ShowPresent(ImpView impView) {
        this.impView = impView;
        showModel=new ShowModel();
    }
    public void getBannerPresent(){
        showModel.getBannerModel();
        showModel.setBannerModel(new ShowModel.getBannerModel() {
            @Override
            public void onBannerModel(List<ShowBannerBean.ResultBean> result) {
                impView.getBannerView(result);
            }
        });
    }

    public void getDataPresent(){
        showModel.getShowData();
        showModel.setShowData(new ShowModel.getDataModel() {
            @Override
            public void onShowData(ShowDataBean showDataBean) {
                impView.getDataView(showDataBean);
            }
        });
    }

    public void getSearchPresent(String keyword,int page,int count){
        showModel.getSearchModel(keyword,page,count);
        showModel.setSearchModel(new ShowModel.getSearchModel() {
            @Override
            public void onSearchModel(List<SearchBean.ResultBean> searchBean) {
                impView.getSearchView(searchBean);
            }
        });
    }

    public void getDstailsPresent(int id){
        showModel.getDstailsModel(id);
        showModel.setDstailsModel(new ShowModel.getDstailsModel() {
            @Override
            public void onDstailsModel(DstailsBean dstailsBean) {
             impView.getDetailsView(dstailsBean);
            }
        });
    }

    public void getShopPresent(int userId, String sessionId, String data){
        showModel.getShopModel(userId,sessionId,data);
        showModel.setShopModel(new ShowModel.getShopModel() {
            @Override
            public void onShopModel(ShopBean shopBean) {
                impView.getShopView(shopBean);
            }
        });
    }

    public void getSSPresent(int userId,String sessionId){
        showModel.getSSmodel(userId,sessionId);
        showModel.setsSmodel(new ShowModel.getSSmodel() {
            @Override
            public void onSSModel(ShopSearchBean searchBean) {
                impView.getSSView(searchBean);
            }
        });
    }

    public void detach(){
        if (impView==null){
            impView=null;
        }
    }
}
