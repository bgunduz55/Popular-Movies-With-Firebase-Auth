package com.example.popularmovies.ui.movielist;

import androidx.lifecycle.ViewModel;

import com.example.popularmovies.models.PopularMovieResult;
import com.example.popularmovies.models.Result;

import com.example.popularmovies.restapi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MovieListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public void getPopularMovies(Callback<PopularMovieResult> callback){
        Call<PopularMovieResult> call = RetrofitClient.getInstance().getMyApi().getPopularMovies();
        call.enqueue(callback);
    }
}