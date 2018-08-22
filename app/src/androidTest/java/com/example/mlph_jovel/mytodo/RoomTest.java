package com.example.mlph_jovel.mytodo;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.mlph_jovel.mytodo.database.AppDatabase;
import com.example.mlph_jovel.mytodo.movies.model.Movies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RoomTest {

    private AppDatabase appDatabase;

    @Before
    public void initDB() throws Exception {

        Context context = InstrumentationRegistry.getContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @Test
    public void countShouldReturnZeroByDefault() {
        Long count = appDatabase.movieDao().count();
        assertEquals(count.intValue(), 0);
    }

    @Test
    public void insertMovieShouldIncrementCount() {

        countShouldReturnZeroByDefault();

        Movies movies = new Movies();
        movies.setMovieId("1");
        movies.setMovieName("Kokomo");

        appDatabase.movieDao().insertOnlySingleMovie(movies);

        Long count = appDatabase.movieDao().count();
        assertEquals(count.intValue(), 1);
    }


    @After
    public void closeDB() throws Exception {
        appDatabase.close();
    }
}
