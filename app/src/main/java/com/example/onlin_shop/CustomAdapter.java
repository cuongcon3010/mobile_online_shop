package com.example.onlin_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List<Item> data;

    public CustomAdapter(Context context, int layoutResourceId, List<Item> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.imageView = row.findViewById(R.id.Item_image);
            holder.textViewTitle = row.findViewById(R.id.Item_title);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = data.get(position);
        holder.textViewTitle.setText(item.getName_item());

        return row;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textViewTitle;

    }

}
