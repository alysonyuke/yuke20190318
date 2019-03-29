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


public class HotsellAdapter extends RecyclerView.Adapter<HotsellAdapter.ViewHolder> {
    Context context;
    ShowDataBean RxxpBean;

    public HotsellAdapter(Context context, ShowDataBean RxxpBean) {
        this.context = context;
        this.RxxpBean = RxxpBean;
    }

    @NonNull
    @Override
    public HotsellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.homesell,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotsellAdapter.ViewHolder viewHolder, final int i) {
        final ShowDataBean.ResultBean.RxxpBean rxxp = RxxpBean.getResult().getRxxp();
        viewHolder.title.setText(rxxp.getCommodityList().get(i).getCommodityName());
        viewHolder.price.setText("￥"+rxxp.getCommodityList().get(i).getPrice());
        //加载图片
        final AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(rxxp.getCommodityList().get(i).getMasterPic())
                .build();
        viewHolder.Image.setController(controller);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ParticularsActivity.class);
                intent.putExtra("id",rxxp.getCommodityList().get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView Image;
        private TextView price,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.hotsell_img);
            price=itemView.findViewById(R.id.hotsell_price);
            title=itemView.findViewById(R.id.hotsell_title);
        }
    }
}
