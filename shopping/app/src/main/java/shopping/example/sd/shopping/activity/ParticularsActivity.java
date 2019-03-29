package shopping.example.sd.shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.bean.AddBeans;
import shopping.example.sd.shopping.bean.DstailsBean;
import shopping.example.sd.shopping.bean.SearchBean;
import shopping.example.sd.shopping.bean.ShopBean;
import shopping.example.sd.shopping.bean.ShopSearchBean;
import shopping.example.sd.shopping.bean.ShowBannerBean;
import shopping.example.sd.shopping.bean.ShowDataBean;
import shopping.example.sd.shopping.mvp.ImpView;
import shopping.example.sd.shopping.mvp.ShowPresent;

public class ParticularsActivity extends AppCompatActivity implements ImpView {

    @BindView(R.id.return_btn)
    TextView returnBtn;
    @BindView(R.id.spxq_banner)
    XBanner spxqBanner;
    @BindView(R.id.spxq_price)
    TextView spxqPrice;
    @BindView(R.id.spxq_sell)
    TextView spxqSell;
    @BindView(R.id.l)
    RelativeLayout l;
    @BindView(R.id.spxq)
    TextView spxq;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.dstails_buy)
    ImageView dstailsBuy;
    @BindView(R.id.dstails_add)
    ImageView dstailsAdd;
    private ShowPresent showPresent;
    private ArrayList list = new ArrayList<>();
    private String sessionId;
    private int userId;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);
        ButterKnife.bind(this);
        showPresent = new ShowPresent((ImpView) this);
        id = getIntent().getIntExtra("id", 0);
        showPresent.getDstailsPresent(id);
        dstailsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                sessionId = user.getString("sessionId", "");
                userId = user.getInt("userId", 0);
                showPresent.getSSPresent(userId, sessionId);
            }
        });
        dstailsBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParticularsActivity.this,OrderFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.return_btn)
    public void onViewClicked() {
        finish();
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
        DstailsBean.ResultBean result = dstailsBean.getResult();
        spxqPrice.setText("￥" + result.getPrice());
        spxqSell.setText("已售" + result.getSaleNum());
        spxq.setText("" + result.getCommodityName());
        //设置轮播图片
        String picture = result.getPicture();
        String[] split = picture.split("\\,");
        for (int i = 0; i < split.length; i++) {//循环添加图片
            list.add(split[i]);
        }
        spxqBanner.setData(list, null);
        spxqBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(ParticularsActivity.this).load(list.get(position)).into((ImageView) view);
            }
        });
        //加载webview
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        String s = "<script type=\"text/javascript\">" +
                "var imgs=document.getElementsByTagName('img');" +
                "for(var i = 0; i<imgs.length; i++){" +
                "imgs[i].style.width='100%';" +
                "imgs[i].style.height='auto';" +
                "}" +
                "</script>";
        webview.loadDataWithBaseURL(null, result.getDetails() + s + "<html><body>", "text/html", "utf-8", null);
    }

    @Override
    public void getShopView(ShopBean shopBean) {
            Toast.makeText(this,""+shopBean.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSSView(ShopSearchBean searchBean) {
        if(searchBean instanceof ShopSearchBean){
            if (searchBean.getStatus().equals("0000")) {
                List<AddBeans> list = new ArrayList<>();
                List<ShopSearchBean.ResultBean> result = searchBean.getResult();
                for (ShopSearchBean.ResultBean results : result) {
                    list.add(new AddBeans(results.getCommodityId(), results.getCount()));
                }
                addShopping(list);
            }
        }
    }

    private void addShopping(List<AddBeans> list) {
        String string = "[";
        if (list.size() == 0) {
            list.add(new AddBeans(id+"", 1));

        } else {
            for (int i = 0; i < list.size(); i++) {
                if (id+"" == list.get(i).getCommodityId()) {
                    int count = list.get(i).getCount();
                    count++;
                    list.get(i).setCount(count);
                    break;
                } else if (i == list.size() - 1) {
                    list.add(new AddBeans(id+"", 1));
                    break;
                }
            }
        }
        for (AddBeans goods : list) {
            string += "{\"commodityId\":" + goods.getCommodityId() + ",\"count\":" + goods.getCount() + "},";
        }
        String substring = string.substring(0, string.length() - 1);
        substring += "]";
        String data=substring;
        showPresent.getShopPresent(userId,sessionId,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresent.detach();
    }
}
