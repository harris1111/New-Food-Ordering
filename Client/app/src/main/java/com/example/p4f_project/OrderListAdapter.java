package com.example.p4f_project;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class OrderListAdapter extends BaseAdapter{
    private List<Product> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public OrderListAdapter(Context aContext,  List<Product> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        if(listData!=null){
            return listData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.activity_order_list_view, null);
            holder = new ViewHolder();
            holder.foodImg = (ImageView) convertView.findViewById(R.id.orderdItemImg);
            holder.foodName = (TextView) convertView.findViewById(R.id.ordered_item_name);
            holder.foodPrice = (TextView) convertView.findViewById(R.id.Orderedtotal);
            holder.foodAmount = (TextView) convertView.findViewById(R.id.Orderedamount);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Product product= this.listData.get(position);
        holder.foodName.setText(product.getName());
        holder.foodPrice.setText("Price: "+String.valueOf(product.getPrice() * product.getAmount())+" VND");
        holder.foodAmount.setText("Amount: "+String.valueOf(product.getAmount()));
        holder.foodImg.setImageResource(getImageID(product.getID()));
        return convertView;
    }


    private int getImageID(String imgName){
        Resources res = context.getResources();
        int resID = res.getIdentifier(imgName , "drawable", context.getPackageName());
        return resID;
    }
    private class ViewHolder {
        ImageView foodImg;
        TextView foodName;
        TextView foodPrice;
        TextView foodAmount;
    }
}
