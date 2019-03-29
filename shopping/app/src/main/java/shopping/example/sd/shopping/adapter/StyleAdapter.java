package shopping.example.sd.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.activity.ParticularsActivity;
import shopping.example.sd.shopping.bean.ShowDataBean;

public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.ViewHolder> {
    Context context;ShowDataBean MlssBean;

    public StyleAdapter(Context context, ShowDataBean MlssBean) {
        this.context = context;
        this.MlssBean = MlssBean;
    }

    @NonNull
    @Override
    public StyleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.homestyle,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StyleAdapter.ViewHolder viewHolder, final int i) {
        final ShowDataBean.ResultBean.MlssBean mlss = MlssBean.getResult().getMlss();
        viewHolder.title.setText(mlss.getCommodityList().get(i).getCommodityName());
        viewHolder.price.setText("￥"+mlss.getCommodityList().get(i).getPrice());
        //加载图片
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(mlss.getCommodityList().get(i).getMasterPic())
                .build();
        viewHolder.Image.setController(controller);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ParticularsActivity.class);
                intent.putExtra("id",mlss.getCommodityList().get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView Image;
        private TextView price,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.style_img);
            price=itemView.findViewById(R.id.style_price);
            title=itemView.findViewById(R.id.style_title);
        }
    }
}
