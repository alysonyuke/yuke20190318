package shopping.example.sd.shopping.show.mvp;

import java.util.List;

import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;

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

    public void detach(){
        if (impView==null){
            impView=null;
        }
    }
}
