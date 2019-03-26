package shopping.example.sd.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shopping.example.sd.shopping.bean.DstailsBean;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;
import shopping.example.sd.shopping.show.adapter.Custom;
import shopping.example.sd.shopping.show.adapter.SearchAdapter;
import shopping.example.sd.shopping.show.mvp.ImpView;
import shopping.example.sd.shopping.show.mvp.ShowPresent;

public class SearchActivity extends AppCompatActivity implements ImpView {

    @BindView(R.id.searchcustom)
    Custom searchcustom;
    @BindView(R.id.searchshow)
    RecyclerView searchshow;
    @BindView(R.id.search_image)
    ImageView searchImage;
    private ShowPresent showPresent;
    private int page = 1;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        showPresent = new ShowPresent(this);
        searchcustom.setSearchData(new Custom.getSearchData() {
            @Override
            public void onRearchData(String s) {
                showPresent.getSearchPresent(s, page, count);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
                searchshow.setLayoutManager(gridLayoutManager);
            }
        });
    }

    @Override
    public void getBannerView(List<ShowBannerBean.ResultBean> result) {

    }

    @Override
    public void getDataView(ShowDataBean showDataBean) {

    }

    @Override
    public void getSearchView(List<SearchBean.ResultBean> searchbean) {
        if (searchbean.size() == 0) {
            searchImage.setVisibility(View.VISIBLE);
        }else {
            searchImage.setVisibility(View.GONE);
            SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, searchbean);
            searchshow.setAdapter(searchAdapter);
        }

    }

    @Override
    public void getDetailsView(DstailsBean dstailsBean) {

    }

    @OnClick({R.id.searchcustom, R.id.searchshow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchcustom:
                break;
            case R.id.searchshow:
                break;
        }
    }
}
