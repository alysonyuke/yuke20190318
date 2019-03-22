package shopping.example.sd.shopping.show.mvp;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import shopping.example.sd.shopping.api.API;
import shopping.example.sd.shopping.api.ApiService;
import shopping.example.sd.shopping.bean.SearchBean;
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
        ApiService apiService = RetrofitUtils.getInstance().doPost(API.bannerUrl, ApiService.class);
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
        ApiService service = RetrofitUtils.getInstance().doPost(API.showDataUrl, ApiService.class);
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
        ApiService doPost = RetrofitUtils.getInstance().doPost(API.searchUrl, ApiService.class);
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
}