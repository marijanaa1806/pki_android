package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Artikal;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends ArrayAdapter<Artikal> {
    private Context mContext;
    private List<Artikal> artikals;

    public ImageListAdapter(Context context, List<Artikal> list) {
        super(context, 0, list);
        mContext = context;
        artikals = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, parent, false);
        }

        Artikal currentArtikal = artikals.get(position);

        // Get the ImageView
        ImageView imageView = listItem.findViewById(R.id.imageView);

        // Set the image resource dynamically based on the src value
        int resourceId = mContext.getResources().getIdentifier(currentArtikal.src, "drawable", mContext.getPackageName());
        imageView.setImageResource(resourceId);

        return listItem;
    }
}
