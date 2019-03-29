package shopping.example.sd.shopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.login.MainActivity;
import shopping.example.sd.shopping.adapter.PagerView;

public class MineFragment extends PagerView {
    private SimpleDraweeView simpleDraweeView;
    private TextView user;
    @Override
    protected void intodata() {

    }

    @Override
    protected void intoview(View view) {
        simpleDraweeView=view.findViewById(R.id.bitmaphead);
        user=view.findViewById(R.id.bitmapusername);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        int userId = sharedPreferences.getInt("userId", 0);
        String nickName = sharedPreferences.getString("nickName", "");
        String headPic = sharedPreferences.getString("headPic", "");
        user.setText(nickName);
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(headPic)
                .build();
        simpleDraweeView.setController(controller);
    }

    @Override
    protected int intoById() {
        return R.layout.minefragment;
    }
}
