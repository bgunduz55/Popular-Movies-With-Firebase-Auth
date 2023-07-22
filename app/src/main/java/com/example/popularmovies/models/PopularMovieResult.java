package com.example.popularmovies.models;

import java.util.ArrayList;

public class PopularMovieResult {
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;

    @Override
    public String toString() {
        return "PopularMovieResult{" +
                "page=" + page +
                ", results=" + results +
                ", total_pages=" + total_pages +
                ", total_results=" + total_results +
                '}';
    }
}
