package com.pristinebs.searchimageyourimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ImgRecylerAdapter extends RecyclerView.Adapter<ImgRecylerAdapter.viewHolder> {

    Context context;
    ArrayList<ImgSearchModel.Image> imageArrayList = new ArrayList<ImgSearchModel.Image>();

    public ImgRecylerAdapter(Context context, ArrayList<ImgSearchModel.Image> imageArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
    }

    @NonNull
    @Override
    public ImgRecylerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_recycle, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgRecylerAdapter.viewHolder holder, int position) {

        Picasso.get().load(imageArrayList.get(position).link)
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView img;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
