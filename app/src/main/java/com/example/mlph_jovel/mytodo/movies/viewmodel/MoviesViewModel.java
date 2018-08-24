package com.example.mlph_jovel.mytodo.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mlph_jovel.mytodo.database.AppDatabase;
import com.example.mlph_jovel.mytodo.movies.model.MovieDao;
import com.example.mlph_jovel.mytodo.movies.model.Movies;

import java.util.List;

public class MoviesViewModel extends ViewModel{

    private LiveData<List<Movies>> movies;
    private MovieDao movieDao;

    public void init(Context context) {
        movieDao = AppDatabase.getDatabaseInstance(context).movieDao();
        movies = movieDao.getAllMovies();
    }

    public LiveData<List<Movies>> getMovies() {
        return movies;
    }
}
