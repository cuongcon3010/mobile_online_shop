package com.example.onlin_shop;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    private Context context;
    private int layoutResourceId;
    private List<Item> data;
    public CustomAdapter(Context context, int layoutResourceId, List<Item> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            //holder.imageView = row.findViewById(R.id.Item_image);
            holder.textViewTitle = row.findViewById(R.id.Item_title);
            holder.textViewSubtitle = row.findViewById(R.id.Item_TG);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = data.get(position);

        // Load image using Glide
//        Glide.with(context)
//                .load(item.getImage())  // Assuming item.getImage() returns a valid URL or resource
//                .into(holder.imageView);

        holder.textViewTitle.setText(item.getName_item());
        holder.textViewSubtitle.setText(item.getGia());

        return row;
    }


    static class ViewHolder {
        //ImageView imageView;
        TextView textViewTitle;
        TextView textViewSubtitle;
    }


}
