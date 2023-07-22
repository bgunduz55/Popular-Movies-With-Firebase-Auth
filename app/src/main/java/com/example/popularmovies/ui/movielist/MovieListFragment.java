package com.example.popularmovies.ui.movielist;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.popularmovies.MainActivity;
import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityMainBinding;
import com.example.popularmovies.databinding.FragmentMovieListBinding;
import com.example.popularmovies.models.PopularMovieResult;
import com.example.popularmovies.models.Result;
import com.example.popularmovies.ui.adapters.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends Fragment {
    FragmentMovieListBinding binding;
    private MovieListViewModel mViewModel;
    List<Result> resultList;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("test 1");
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("test 2");
        binding = FragmentMovieListBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("test 3");

//        setContentView(binding.getRoot());
        resultList = new ArrayList<>();
        //movieRecycler = findViewById(R.id.movie_recycler);

        final MovieAdapter adapter = new MovieAdapter(getActivity(), resultList);
        binding.movieRecycler.setAdapter(adapter);
        binding.movieRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        mViewModel.getPopularMovies(new Callback<PopularMovieResult>() {
            @Override
            public void onResponse(Call<PopularMovieResult> call, Response<PopularMovieResult> response) {

//        binding = FragmentMovieListBinding.inflate(getLayoutInflater());

                if(response.isSuccessful() && response.body() != null && !response.body().results.isEmpty()){
                    System.out.println(response.body().toString());
                    adapter.setItems(response.body().results);

                    adapter.notifyDataSetChanged();
//                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                }
            }

            @Override
            public void onFailure(Call<PopularMovieResult> call, Throwable t) {
                Toast.makeText(getActivity(), "Hata oluştu!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        // TODO: Use the ViewModel
    }
}