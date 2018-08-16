package com.example.mlph_jovel.mytodo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mlph_jovel.mytodo.MovieDao;
import com.example.mlph_jovel.mytodo.Movies;

@Database(entities = { Movies.class }, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
}
