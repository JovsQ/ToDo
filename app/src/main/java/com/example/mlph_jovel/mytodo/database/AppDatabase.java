package com.example.mlph_jovel.mytodo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mlph_jovel.mytodo.movies.model.MovieDao;
import com.example.mlph_jovel.mytodo.movies.model.Movies;

@Database(entities = { Movies.class }, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public static AppDatabase instance;

    public static synchronized AppDatabase getDatabaseInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }

        return instance;
    }


    static AppDatabase create(Context context) {
        RoomDatabase.Builder<AppDatabase> builder =
                Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "movies");

        return builder.build();
    }
}
