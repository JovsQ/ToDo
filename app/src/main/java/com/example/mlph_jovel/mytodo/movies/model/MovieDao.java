package com.example.mlph_jovel.mytodo.movies.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOnlySingleMovie(Movies movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleMovies(List<Movies> moviesList);

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    Movies fetchMoviesByMovieId(String movieId);

    @Query("SELECT * FROM movies")
    List<Movies> fetchAllMovies();

    @Query("SELECT * FROM movies")
    LiveData<List<Movies>> getAllMovies();

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    LiveData<Movies> getMovieById(String movieId);

    @Query("SELECT COUNT(*) FROM movies")
    Long count();

    @Delete
    void deleteMovie(Movies movies);
}
