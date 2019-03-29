package shopping.example.sd.shopping.mvp;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
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

public class ShowModel {
    public interface getBannerModel{
        void onBannerModel(List<ShowBannerBean.ResultBean> result);
    }
    getBannerModel bannerModel;

    public void setBannerModel(getBannerModel bannerModel) {
        this.bannerModel = bannerModel;
    }
    public void getBannerModel(){
        ApiService apiService = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
        apiService.getBannerUrl().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShowBannerBean>() {
                    @Override
                    public void onNext(ShowBannerBean bannerBean) {
                        List<ShowBannerBean.ResultBean> result = bannerBean.getResult();
                        if (bannerModel!=null){
                            bannerModel.onBannerModel(result);
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

    public interface getDataModel{
        void onShowData(ShowDataBean showDataBean);
    }
    getDataModel dataModel;

    public void setShowData(getDataModel dataModel) {
        this.dataModel = dataModel;
    }
    public  void getShowData(){
        ApiService service = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
        Flowable<ShowDataBean> showDataUrl = service.getShowDataUrl();
        showDataUrl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShowDataBean>() {
                    @Override
                    public void onNext(ShowDataBean showDataBean) {
                        if (dataModel!=null){
                            dataModel.onShowData(showDataBean);
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

    public interface getSearchModel{
        void onSearchModel(List<SearchBean.ResultBean> searchBean);
    }
    getSearchModel searchModel;

    public void setSearchModel(getSearchModel searchModel) {
        this.searchModel = searchModel;
    }
    public void getSearchModel(String keyword,int page,int count){
        ApiService doPost = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
        doPost.getSearchUrl(keyword,page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.ResultBean> result = searchBean.getResult();
                        if (searchModel!=null){
                            searchModel.onSearchModel(result);
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

    public interface getDstailsModel{
        void onDstailsModel(DstailsBean dstailsBean);
    }
    getDstailsModel dstailsModel;

    public void setDstailsModel(getDstailsModel dstailsModel) {
        this.dstailsModel = dstailsModel;
    }
    public void getDstailsModel(int id){
        ApiService apiService = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
        apiService.getDetailsUrl(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<DstailsBean>() {
                    @Override
                    public void onNext(DstailsBean dstailsBean) {
                        if (dstailsModel!=null){
                            dstailsModel.onDstailsModel(dstailsBean);
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
    public interface getShopModel{
        void onShopModel(ShopBean shopBean);
    }
    getShopModel shopModel;

    public void setShopModel(getShopModel shopModel) {
        this.shopModel = shopModel;
    }

    public void getShopModel(int userId, String sessionId, String data){
         ApiService apiService = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
         apiService.getShopUrl(userId,sessionId,data).subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(new DisposableSubscriber<ShopBean>() {
                     @Override
                     public void onNext(ShopBean shopBean) {
                         if (shopModel!=null){
                             shopModel.onShopModel(shopBean);
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

    public interface getSSmodel{
        void onSSModel(ShopSearchBean searchBean);
    }
    getSSmodel sSmodel;

    public void setsSmodel(getSSmodel sSmodel) {
        this.sSmodel = sSmodel;
    }
    public void getSSmodel(int userId,String sessionId){
        ApiService apiService = RetrofitUtils.getInstance().doPost(API.Url, ApiService.class);
        apiService.getWatchUrl(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShopSearchBean>() {
                    @Override
                    public void onNext(ShopSearchBean searchBean) {
                        if (sSmodel!=null){
                            sSmodel.onSSModel(searchBean);
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
