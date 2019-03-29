package shopping.example.sd.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import shopping.example.sd.shopping.R;
import shopping.example.sd.shopping.bean.ShopSearchBean;

public class ShopAdapter extends XRecyclerView.Adapter<ShopAdapter.ViewHolder> {
    Context context;
    List<ShopSearchBean.ResultBean> list;

    public ShopAdapter(Context context, List<ShopSearchBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }
    /**
     * 接口回调
     */
    public interface getClickkListener {
        //item 选中接口回调
        void onChecked(int position, boolean isChecked);
        //删除回调
        void onDelete(int position);
    }

    public getClickkListener clickkListener;

    public void setCallbackListener(getClickkListener clickkListener) {
        this.clickkListener = clickkListener;
    }
    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.shop_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(list.get(i).getCommodityName());
        viewHolder.price.setText(list.get(i).getPrice());
        viewHolder.Image.setImageURI(list.get(i).getPic());
        viewHolder.checkBox.setChecked(list.get(i).isIschecked());
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickkListener!=null){
                    clickkListener.onChecked(i,viewHolder.checkBox.isChecked());
                }
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickkListener.onDelete(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView Image;
        private TextView title,price,delete;
        private CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.shop_img);
            title=itemView.findViewById(R.id.shop_title);
            price=itemView.findViewById(R.id.shop_price);
            checkBox=itemView.findViewById(R.id.checkbox);
            delete=itemView.findViewById(R.id.shop_delete);
        }
    }
}
