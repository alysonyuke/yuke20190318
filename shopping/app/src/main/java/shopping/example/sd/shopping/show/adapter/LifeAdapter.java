package shopping.example.sd.shopping.show.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.bean.ShowDataBean;

public class LifeAdapter extends RecyclerView.Adapter<LifeAdapter.ViewHolder> {
    Context context;ShowDataBean PzshBean ;

    public LifeAdapter(Context context, ShowDataBean PzshBean) {
        this.context = context;
        this.PzshBean = PzshBean;
    }

    @NonNull
    @Override
    public LifeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.homelife,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LifeAdapter.ViewHolder viewHolder, int i) {
        ShowDataBean.ResultBean.PzshBean Pzss = PzshBean.getResult().getPzsh();
        viewHolder.title.setText(Pzss.getCommodityList().get(i).getCommodityName());
        viewHolder.price.setText("￥"+Pzss.getCommodityList().get(i).getPrice());
        //加载图片
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Pzss.getCommodityList().get(i).getMasterPic())
                .build();
        viewHolder.image.setController(controller);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView title,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.life_img);
            title=itemView.findViewById(R.id.life_title);
            price=itemView.findViewById(R.id.life_price);
        }
    }
}
