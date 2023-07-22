package com.example.popularmovies.ui.adapters;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.databinding.MovieAdapterLayoutBinding;
import com.example.popularmovies.models.Result;

public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MovieAdapterLayoutBinding binding;
    public MovieHolder(MovieAdapterLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
//        this.listener=listener;
        binding.getRoot().setOnClickListener(this);
    }

    public void bind(Result item) {
//        binding.setItem(item);
        System.out.println(item.title);
    }

    @Override
    public void onClick(View view) {
//        listener.onItemClick(getAdapterPosition());
    }
}
