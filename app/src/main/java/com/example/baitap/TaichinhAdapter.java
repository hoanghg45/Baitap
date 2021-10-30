package com.example.baitap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.Other.ItemClickListener;

import java.util.ArrayList;

public class TaichinhAdapter extends RecyclerView.Adapter<TaichinhAdapter.ViewHolder> {
    private ArrayList<Taichinh> alTc;
    private Context context;
    private TaichinhAdapterListener taichinhAdapterListenerAdapterListener;

    public TaichinhAdapter(ArrayList<Taichinh> alTc, Context context, TaichinhAdapterListener taichinhAdapterListenerAdapterListener) {
        this.alTc = alTc;
        this.context = context;
        this.taichinhAdapterListenerAdapterListener = taichinhAdapterListenerAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.taichinh_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(String.valueOf(alTc.get(position).getName()));
        holder.txtPrice.setText(String.valueOf(alTc.get(position).getPrice()));
        if (alTc.get(position).getType() == 0){
            holder.layoutItem.setBackgroundColor(Color.parseColor("#A15959"));
        }
        else{
            holder.layoutItem.setBackgroundColor(Color.parseColor("#86B888"));
        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                taichinhAdapterListenerAdapterListener.click(v,position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return alTc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtName,txtPrice;
        private LinearLayout layoutItem;
        private Button btnSua,btnXoa;
        public ItemClickListener itemClickListener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            layoutItem = itemView.findViewById(R.id.layoutItem);


            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getAdapterPosition());
        }
        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
    public interface TaichinhAdapterListener {
        void click(View v, int position);
    }
}
