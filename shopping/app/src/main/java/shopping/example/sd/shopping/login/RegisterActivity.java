package shopping.example.sd.shopping.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.login.bean.LoginBean;
import shopping.example.sd.shopping.login.bean.RegsinBean;
import shopping.example.sd.shopping.login.mvp.ImpView;
import shopping.example.sd.shopping.login.mvp.LoginPresenter;
import shopping.example.sd.shopping.show.ShowActivity;

public class RegisterActivity extends AppCompatActivity implements ImpView {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.yz)
    EditText yz;
    @BindView(R.id.hqyz)
    TextView hqyz;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eyes)
    ImageView eyes;
    @BindView(R.id.have)
    TextView have;
    @BindView(R.id.regsin)
    Button regsin;
    private boolean eye=true;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);

    }

    @OnClick({R.id.phone, R.id.pwd, R.id.eyes, R.id.have, R.id.regsin})
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
            case R.id.have:
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);finish();
                break;
            case R.id.regsin:
                String number = phone.getText().toString();
                String pass = pwd.getText().toString();
                loginPresenter.getRegsinPresenter(number,pass);
                break;
        }
    }

    @Override
    public void getLoginView(LoginBean.ResultBean loginBean) {

    }

    @Override
    public void getRegsinView(RegsinBean regsinBean) {
        if (regsinBean instanceof RegsinBean) {
            Toast.makeText(this, regsinBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(regsinBean.getMessage().equals("注册成功")){
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }
        }


    }
}
