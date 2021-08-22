package com.example.p4f_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class restaurantAdapter extends RecyclerView.Adapter<restaurantAdapter.ResViewHolder>{
    private List<Restaurant> listRestaurant;

    public restaurantAdapter(List<Restaurant> listRestaurant) {
        this.listRestaurant = listRestaurant;
    }

    @NonNull
    @Override
    public ResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant,parent,false);
        return new ResViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResViewHolder holder, int position) {
         Restaurant res=listRestaurant.get(position);
         if(res==null){
             return;
         }
         holder.imgRes.setImageResource(res.getImage());
         holder.tv_resName.setText(res.getName());
         holder.tv_resAdd.setText(res.getAddress());
    }

    @Override
    public int getItemCount() {
        if(listRestaurant!=null){
            return listRestaurant.size();
        }
        return 0;
    }

    public class ResViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView imgRes;
        private TextView tv_resName;
        private TextView tv_resAdd;
        public ResViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRes=itemView.findViewById(R.id.resImg1);
            tv_resName=itemView.findViewById(R.id.tv_res1Name);
            tv_resAdd=itemView.findViewById(R.id.tv_res1Address);
        }
    }
}
