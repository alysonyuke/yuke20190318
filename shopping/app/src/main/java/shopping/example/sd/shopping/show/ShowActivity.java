package shopping.example.sd.shopping.show;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.show.fragment.CricleFragment;
import shopping.example.sd.shopping.show.fragment.HomeFragment;
import shopping.example.sd.shopping.show.fragment.MineFragment;
import shopping.example.sd.shopping.show.fragment.OrderFragment;
import shopping.example.sd.shopping.show.fragment.ShopFragment;

public class ShowActivity extends AppCompatActivity {


    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.circle)
    RadioButton circle;
    @BindView(R.id.shopping)
    RadioButton shopping;
    @BindView(R.id.order)
    RadioButton order;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    private HomeFragment homeFragment;
    private CricleFragment cricleFragment;
    private ShopFragment shopFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        initview(new HomeFragment());
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home:
                        initview(new HomeFragment());break;
                    case R.id.circle:
                        initview(new CricleFragment());break;
                    case R.id.shopping:
                        initview(new ShopFragment());break;
                    case R.id.order:
                        initview(new OrderFragment());break;
                    case R.id.mine:
                        initview(new MineFragment());break;
                }
            }
        });
    }

    private void initview(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction replace = supportFragmentManager.beginTransaction().replace(R.id.framelayout, fragment);
        replace.commit();
    }


    @OnClick({R.id.framelayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.framelayout:

        }
    }
}
