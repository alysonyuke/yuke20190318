package shopping.example.sd.shopping.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.activity.ShowActivity;
import shopping.example.sd.shopping.login.bean.LoginBean;
import shopping.example.sd.shopping.login.bean.RegsinBean;
import shopping.example.sd.shopping.login.mvp.ImpView;
import shopping.example.sd.shopping.login.mvp.LoginPresenter;

public class MainActivity extends AppCompatActivity implements ImpView {
    public boolean eye=true;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eyes)
    ImageView eyes;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.resign)
    TextView resign;
    @BindView(R.id.login)
    Button login;
    private LoginPresenter loginPresenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        sharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean ischeck = sharedPreferences.getBoolean("ischeck", false);
        if (ischeck){
            String number = sharedPreferences.getString("phone", null);
            String pass = sharedPreferences.getString("pass", null);
            phone.setText(number);
            pwd.setText(pass);
            check.setChecked(true);
        }
        editor.commit();
    }
    @OnClick({R.id.phone, R.id.pwd, R.id.eyes, R.id.check, R.id.resign, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone:
                break;
            case R.id.pwd:
                break;
            case R.id.eyes:
                if (eye){
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye=false;
                }else {
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye=true;
                }
                break;
            case R.id.check:

                break;
            case R.id.resign:
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);finish();
                break;
            case R.id.login:
                String number = phone.getText().toString();
                String pass = pwd.getText().toString();
                if (check.isChecked()){
                    editor.putString("phone",number);
                    editor.putString("pass",pass);
                    editor.putBoolean("ischeck",true);
                    editor.commit();
                }else {
                    editor.clear();
                }
                loginPresenter.getLoginPresenter(number,pass);
                break;
        }
    }

    @Override
    public void getLoginView(LoginBean.ResultBean loginBean) {

        if(loginBean instanceof LoginBean.ResultBean){
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
            sessionId = loginBean.getSessionId();
            userId = loginBean.getUserId();
            String headPic = loginBean.getHeadPic();
            String phone = loginBean.getPhone();
            int sex = loginBean.getSex();
            String nickName = loginBean.getNickName();
            editor.putString("sessionId",sessionId);
            editor.putInt("userId",userId);
            editor.putString("headPic",headPic);
            editor.putString("phone",phone);
            editor.putString("sex",sex+"");
            editor.putString("nickName",nickName);
            editor.commit();


            Intent intent1=new Intent(MainActivity.this,ShowActivity.class);
            startActivity(intent1);finish();
        }else {
            Toast.makeText(this,"登陆失败，请重新登陆",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getRegsinView(RegsinBean regsinBean) {

    }
}
