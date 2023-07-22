package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.popularmovies.databinding.ActivityMainBinding;
import com.example.popularmovies.databinding.FragmentMovieListBinding;
import com.example.popularmovies.models.PopularMovieResult;
import com.example.popularmovies.models.Result;
import com.example.popularmovies.ui.adapters.MovieAdapter;
import com.example.popularmovies.ui.movielist.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MovieListViewModel mViewModel;
    List<Result> resultList;
    //RecyclerView movieRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        resultList = new ArrayList<>();
        //movieRecycler = findViewById(R.id.movie_recycler);

        final MovieAdapter adapter = new MovieAdapter(MainActivity.this, resultList);
        binding.movieRecycler.setAdapter(adapter);
        binding.movieRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        mViewModel.getPopularMovies(new Callback<PopularMovieResult>() {
            @Override
            public void onResponse(Call<PopularMovieResult> call, Response<PopularMovieResult> response) {

//        binding = FragmentMovieListBinding.inflate(getLayoutInflater());

                if(response.isSuccessful() && response.body() != null && !response.body().results.isEmpty()){
                    System.out.println(response.body().toString());
                    adapter.setItems(response.body().results);

                    adapter.notifyDataSetChanged();
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                }
            }

            @Override
            public void onFailure(Call<PopularMovieResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hata oluştu!", Toast.LENGTH_LONG).show();
            }
        });
    }
}