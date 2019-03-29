package shopping.example.sd.shopping.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.activity.SearchActivity;
import shopping.example.sd.shopping.bean.DstailsBean;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShopBean;
import shopping.example.sd.shopping.bean.ShopSearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;
import shopping.example.sd.shopping.adapter.Custom;
import shopping.example.sd.shopping.adapter.HotsellAdapter;
import shopping.example.sd.shopping.adapter.LifeAdapter;
import shopping.example.sd.shopping.adapter.PagerView;
import shopping.example.sd.shopping.adapter.SearchAdapter;
import shopping.example.sd.shopping.adapter.StyleAdapter;
import shopping.example.sd.shopping.mvp.ImpView;
import shopping.example.sd.shopping.mvp.ShowPresent;

public class HomeFragment extends PagerView implements ImpView {
    private XBanner xBanner;
    private ShowPresent showPresent;
    private RecyclerView hotsell,style,life;
    private Custom custom;
    private ShowPresent show;
    private int page=1;
    private int count=10;
    private RecyclerView rec;
    private TextView search;
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
        showPresent.getDataPresent();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        style.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        hotsell.setLayoutManager(gridLayoutManager);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        life.setLayoutManager(manager);
        search=view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
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
                    public void loadBanner(XBanner banner, Object model, View view, final int position) {
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
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        rec.setLayoutManager(gridLayoutManager1);
        SearchAdapter searchAdapter = new SearchAdapter(getActivity(), searchbean);
        rec.setAdapter(searchAdapter);
    }

    @Override
    public void getDetailsView(DstailsBean dstailsBean) {

    }

    @Override
    public void getShopView(ShopBean shopBean) {

    }

    @Override
    public void getSSView(ShopSearchBean searchBean) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showPresent.detach();
    }
}
