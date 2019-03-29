package shopping.example.sd.shopping.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.adapter.PagerView;
import shopping.example.sd.shopping.adapter.ShopAdapter;
import shopping.example.sd.shopping.bean.DstailsBean;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShopBean;
import shopping.example.sd.shopping.bean.ShopSearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;
import shopping.example.sd.shopping.mvp.ImpView;
import shopping.example.sd.shopping.mvp.ShowPresent;

public class ShopFragment extends PagerView implements ImpView,ShopAdapter.getClickkListener {

    private int page=0;
    private ShowPresent showPresent;
    private int userId;
    private String sessionId;
    private ShopAdapter shopAdapter;
    private XRecyclerView shopRecycerview;
    private List<ShopSearchBean.ResultBean> result;
    private CheckBox all;
    private TextView total;

    @Override
    protected void intodata() {

    }


    @Override
    protected void intoview(View view) {
        shopRecycerview=view.findViewById(R.id.shop_recycerview);
        total=view.findViewById(R.id.shop_total);
        all=view.findViewById(R.id.checkboxall);
        showPresent = new ShowPresent(this);
        SharedPreferences user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        userId = user.getInt("userId", 0);
        sessionId = user.getString("sessionId", "");
        shopRecycerview.setPullRefreshEnabled(true);
        shopRecycerview.setLoadingMoreEnabled(true);
        shopRecycerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopRecycerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        showPresent.getSSPresent(userId,sessionId);
        shopRecycerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新中....", Toast.LENGTH_SHORT).show();
                shopRecycerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkall(all.isChecked());
            }
        });
    }
    private void checkall(boolean checked) {
        for (int i = 0; i <result.size() ; i++) {
            result.get(i).setIschecked(checked);
        }
        setprice();
    }
    private void setprice() {
        double priceAll=0.0;
        int num=0;

        //循环集合判断选中状态
        for (int i = 0; i < result.size() ; i++) {
            //如果为true获取价格和数量
            if(result.get(i).isIschecked()){
                priceAll+=Double.parseDouble(result.get(i).getPrice())*result.get(i).getCount();
                num += result.get(i).getCount();
            }
        }
        total.setText("合计:"+priceAll);
        setAdapter();
    }
    @Override
    protected int intoById() {
        return R.layout.shopfragment;
    }

    @Override
    public void getBannerView(List<ShowBannerBean.ResultBean> result) {

    }

    @Override
    public void getDataView(ShowDataBean showDataBean) {

    }

    @Override
    public void getSearchView(List<SearchBean.ResultBean> searchbean) {

    }

    @Override
    public void getDetailsView(DstailsBean dstailsBean) {

    }

    @Override
    public void getShopView(ShopBean shopBean) {

    }

    @Override
    public void getSSView(ShopSearchBean searchBean) {
        result = searchBean.getResult();
        setAdapter();
    }

    private void setAdapter() {
        if(shopAdapter==null){
            shopAdapter=new ShopAdapter(getActivity(),result);
            shopRecycerview.setAdapter(shopAdapter);
            shopAdapter.setCallbackListener(this);
        }else {
            shopAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onChecked(int position, boolean isChecked) {
        result.get(position).setIschecked(isChecked);
        setprice();
    }



    @Override
    public void onDelete(int position) {
        result.remove(position);
        Gson gson = new Gson();
        String s = gson.toJson(result);
        showPresent.getShopPresent(userId,sessionId,s);
        setprice();
    }
}
