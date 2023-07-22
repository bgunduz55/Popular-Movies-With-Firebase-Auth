package com.example.popularmovies.restapi;

import com.example.popularmovies.models.PopularMovieResult;
import com.example.popularmovies.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://api.themoviedb.org/3/movie/";
    @GET("popular?api_key=5aab5329eaa6df1061d2e67446572e1b&language=en-US&page=1")
    Call<PopularMovieResult> getPopularMovies();
}
