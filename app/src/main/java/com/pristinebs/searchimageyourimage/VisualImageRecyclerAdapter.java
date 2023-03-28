package com.pristinebs.searchimageyourimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VisualImageRecyclerAdapter extends RecyclerView.Adapter<VisualImageRecyclerAdapter.viewHolder> {

    Context context;
    ArrayList<ImgSearchModel.VisualMatch> visualMatchArrayList = new ArrayList<>();

    public VisualImageRecyclerAdapter(Context context, ArrayList<ImgSearchModel.VisualMatch> visualMatchArrayList) {
        this.context = context;
        this.visualMatchArrayList = visualMatchArrayList;
    }

    @NonNull
    @Override
    public VisualImageRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visual_imge_recycler, parent, false);
        return new  viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisualImageRecyclerAdapter.viewHolder holder, int position) {

        Picasso.get().load(visualMatchArrayList.get(position).thumbnail)
                .into(holder.imgSearchCover);
        Picasso.get().load(visualMatchArrayList.get(position).source_icon)
                .into(holder.imgSource);

        holder.tvSource.setText(visualMatchArrayList.get(position).source);
        holder.tvDescription.setText(visualMatchArrayList.get(position).title+"...");
    }

    @Override
    public int getItemCount() {
        return visualMatchArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgSearchCover,imgSource;
        TextView tvSource,tvDescription;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imgSearchCover = itemView.findViewById(R.id.imgSearchCover);
            imgSource = itemView.findViewById(R.id.imgSource);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
