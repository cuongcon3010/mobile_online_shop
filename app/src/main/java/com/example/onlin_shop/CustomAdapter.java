package com.example.onlin_shop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List<User> data;

    public CustomAdapter(Context context, int layoutResourceId, List<User> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
}
