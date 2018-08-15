package com.example.mlph_jovel.mytodo;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface DaoAccess {

    @Insert
    void insertOnlySingleMovie(Movies movie);

    @Insert
    void insertMultipleMovies(List<Movies> moviesList);

    @Query("SELECT * FROM Movies WHERE movieId = :movieId")
    Movies fetchMoviesByMovieId(int movieId);

    @Update
    void updateMovie(Movies movies);

    @Delete
    void deleteMovie(Movies movies);
}
