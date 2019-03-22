package shopping.example.sd.shopping.show.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import shopping.example.sd.shopping.R;

public class Custom extends LinearLayout {
    private EditText edit;
    private TextView text;
    public interface getSearchData{
        void onRearchData(String s);
    }
    getSearchData searchData;

    public void setSearchData(getSearchData searchData) {
        this.searchData = searchData;
    }

    public Custom(Context context) {
        super(context);
    }

    public Custom(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom,this);
        edit=findViewById(R.id.edit_search);
        text=findViewById(R.id.text_search);
        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(context,"请输入你要搜素的内容",Toast.LENGTH_SHORT).show();
                }else {
                    searchData.onRearchData(s);
                }
            }
        });
    }

    public Custom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
