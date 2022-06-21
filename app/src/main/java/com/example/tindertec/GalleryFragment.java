package com.example.tindertec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.tindertec.Adapters.PhotoLibraryAdapter;

public class GalleryFragment extends AppCompatActivity {
    GridView gridViewGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);
        gridViewGallery = findViewById(R.id.grid_view_gallery);
        gridViewGallery.setAdapter(new PhotoLibraryAdapter(this));
    }
}