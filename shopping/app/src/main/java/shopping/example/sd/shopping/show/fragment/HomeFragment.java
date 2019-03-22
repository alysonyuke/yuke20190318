package shopping.example.sd.shopping.show.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;
import shopping.example.sd.shopping.show.adapter.Custom;
import shopping.example.sd.shopping.show.adapter.HotsellAdapter;
import shopping.example.sd.shopping.show.adapter.LifeAdapter;
import shopping.example.sd.shopping.show.adapter.PagerView;
import shopping.example.sd.shopping.show.adapter.SearchAdapter;
import shopping.example.sd.shopping.show.adapter.StyleAdapter;
import shopping.example.sd.shopping.show.mvp.ImpView;
import shopping.example.sd.shopping.show.mvp.ShowPresent;

public class HomeFragment extends PagerView implements ImpView {
    private XBanner xBanner;
    private ShowPresent showPresent;
    private RecyclerView hotsell,style,life;
    private Custom custom;
    private ShowPresent show;
    private int page=1;
    private int count=10;
    private RecyclerView rec;
    @Override
    protected void intodata() {
    }
    @Override
    protected void intoview(View view) {
        xBanner=view.findViewById(R.id.xbanner);
        hotsell=view.findViewById(R.id.show_hotsell);
        style=view.findViewById(R.id.show_style);
        life=view.findViewById(R.id.show_life);
        rec=view.findViewById(R.id.searchshow);
        showPresent = new ShowPresent((ImpView)this);
        showPresent.getBannerPresent();
        ShowPresent present = new ShowPresent(this);
        showPresent.getDataPresent();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        style.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        hotsell.setLayoutManager(gridLayoutManager);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        life.setLayoutManager(manager);
        custom=view.findViewById(R.id.customview);
        show = new ShowPresent(this);
        custom.setSearchData(new Custom.getSearchData() {
            @Override
            public void onRearchData(String s) {
                rec.setVisibility(View.VISIBLE);
                show.getSearchPresent(s,page,count);

            }
        });
    }
    @Override
    protected int intoById() {
        return R.layout.homefragment;
    }
    @Override
    public void getBannerView(final List<ShowBannerBean.ResultBean> result) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                xBanner.setData(result,null);
                xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(getActivity())
                                .load(result.get(position).getImageUrl())
                                .into((ImageView) view);
                        xBanner.setPageChangeDuration(3000);
                    }
                });
            }
        });
    }

    @Override
    public void getDataView(ShowDataBean showDataBean) {
        HotsellAdapter hotsellAdapter = new HotsellAdapter(getActivity(), showDataBean);
        hotsell.setAdapter(hotsellAdapter);
        StyleAdapter styleAdapter = new StyleAdapter(getActivity(), showDataBean);
        style.setAdapter(styleAdapter);
        LifeAdapter lifeAdapter = new LifeAdapter(getActivity(), showDataBean);
        life.setAdapter(lifeAdapter);
    }

    @Override
    public void getSearchView(List<SearchBean.ResultBean> searchbean) {
        Log.i("xxxx",searchbean.get(0).getCommodityName());
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        rec.setLayoutManager(gridLayoutManager1);
        SearchAdapter searchAdapter = new SearchAdapter(getActivity(), searchbean);
        rec.setAdapter(searchAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showPresent.detach();
    }
}
