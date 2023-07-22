package com.example.popularmovies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.popularmovies.databinding.ActivityMovieDetailBinding;
import com.example.popularmovies.models.Result;
import com.example.popularmovies.ui.adapters.MovieAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.popularmovies.databinding.ActivityMovieDetailBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Result result = (Result) intent.getSerializableExtra("Movie");

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Bitmap bmImg = null;
//                    bmImg = BitmapFactory.decodeStream((InputStream)new URL("https://image.tmdb.org/t/p/w500" + result.backdrop_path).getContent());
//                    BitmapDrawable background = new BitmapDrawable(bmImg);
//                    binding.toolbarLayout.setBackground(background);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.run();
        new DownloadImageFromInternet(binding.toolbarLayout, result).execute("https://image.tmdb.org/t/p/w500" + result.backdrop_path);
        binding.scrollContent.scrollTextContent.setText(result.overview);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        CollapsingToolbarLayout layout;
        Result result;
        public DownloadImageFromInternet(CollapsingToolbarLayout layout, Result result) {
            this.layout=layout;
            this.result = result;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
//                Bitmap bmImg = null;
//                bmImg = BitmapFactory.decodeStream((InputStream)new URL("https://image.tmdb.org/t/p/w500" + result.backdrop_path).getContent());
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
//                BitmapDrawable background = new BitmapDrawable(bmImg);
//                layout.setBackground(background);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
//            imageView.setImageBitmap(result);
            BitmapDrawable background = new BitmapDrawable(result);
            layout.setBackground(background);
        }
    }
}