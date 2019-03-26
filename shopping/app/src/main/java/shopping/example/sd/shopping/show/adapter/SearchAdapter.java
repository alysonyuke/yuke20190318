package shopping.example.sd.shopping.show.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import shopping.example.sd.shopping.ParticularsActivity;
import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.bean.SearchBean;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    List<SearchBean.ResultBean> searchbean;

    public SearchAdapter(Context context, List<SearchBean.ResultBean> searchbean) {
        this.context = context;
        this.searchbean = searchbean;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.homelife,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(searchbean.get(i).getCommodityName());
        viewHolder.price.setText(searchbean.get(i).getPrice()+"");
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(searchbean.get(i).getMasterPic())
                .build();
        viewHolder.image.setController(controller);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ParticularsActivity.class);
                intent.putExtra("id",searchbean.get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchbean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView title,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.life_title);
            price=itemView.findViewById(R.id.life_price);
            image=itemView.findViewById(R.id.life_img);
        }
    }
}
