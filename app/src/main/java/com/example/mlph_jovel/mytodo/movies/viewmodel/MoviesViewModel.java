package com.example.mlph_jovel.mytodo.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.mlph_jovel.mytodo.database.AppDatabase;
import com.example.mlph_jovel.mytodo.movies.model.MovieDao;
import com.example.mlph_jovel.mytodo.movies.model.Movies;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel{

    private LiveData<List<Movies>> movies;
    private MovieDao movieDao;

    public MoviesViewModel(@NonNull Application application) {
        super(application);

        movieDao = AppDatabase.getDatabaseInstance(application).movieDao();
        movies = movieDao.getAllMovies();
    }

    public LiveData<List<Movies>> getMovies() {
        return movies;
    }
}
