package com.example.popularmovies.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.MovieDetailActivity;
import com.example.popularmovies.R;
import com.example.popularmovies.databinding.MovieAdapterLayoutBinding;
import com.example.popularmovies.models.Result;

import java.io.InputStream;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    List<Result> items;
//    Bitmap posterImage = null;

//    IProductEventListener eventListener;

    public MovieAdapter(Context context,List<Result> items) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
//        this.eventListener = eventListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        binding = MovieAdapterLayoutBinding.inflate(LayoutInflater.from(context));
        View view = inflater.inflate(R.layout.movie_adapter_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(MovieAdapterLayoutBinding.inflate(LayoutInflater.from(context)));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Result p = items.get(position);
        holder.binding.movieTitle.setText(p.title);
        if(holder.posterImage == null){
            new DownloadImageFromInternet((ImageView) holder.binding.movieImage, holder).execute("https://image.tmdb.org/t/p/w500" + p.poster_path);
        } else {
            holder.binding.movieImage.setImageBitmap(holder.posterImage);
        }
        holder.binding.movieImdb.setText(p.vote_average + "/10 IMDB");
        holder.binding.movieSpecialty.setText("" + p.vote_count);
        holder.binding.movieLength.setText(p.release_date);
        holder.binding.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("Movie", p);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
//        TextView nameTv;
        MovieAdapterLayoutBinding binding;
        Bitmap posterImage;

        public MyViewHolder(@NonNull MovieAdapterLayoutBinding bindingx) {
            super(bindingx.getRoot());
            binding = bindingx;

        }
    }

    public void setItems(List<Result> items) {
        this.items = items;
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        MyViewHolder holder;
        public DownloadImageFromInternet(ImageView imageView, MyViewHolder holder) {
            this.imageView=imageView;
            this.holder = holder;
            Toast.makeText(context, "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
                holder.posterImage = bimage;
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}