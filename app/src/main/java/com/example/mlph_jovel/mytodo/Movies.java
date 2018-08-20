package com.example.mlph_jovel.mytodo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Movies {

    @NonNull
    @PrimaryKey
    private String movieId;
    private String movieName;

    public Movies() {}

    public Movies(String movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;
    }

    @NonNull
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(@NonNull String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
