package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
public class MainFragment extends Fragment {

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        imageView = view.findViewById(R.id.imageViewFragment);
        Bundle bundle = getArguments();

        if (bundle != null) {
            String title = bundle.getString("title");
            // Use the title to determine which image to display
            int imageResourceId = getImageResourceForTitle(title);
            imageView.setImageResource(imageResourceId);
        }

        return view;
    }

    // Map titles to corresponding image resources
    private int getImageResourceForTitle(String title) {
        switch (title) {
            case "Torte":
                return R.drawable.cake; // Replace with your actual image resource
            case "Kolaci":
                return R.drawable.ck; // Replace with your actual image resource
            default:
                return 0;
        }
    }
}
