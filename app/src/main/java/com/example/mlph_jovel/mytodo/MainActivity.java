package com.example.mlph_jovel.mytodo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mlph_jovel.mytodo.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String DATABASE_NAME = "movies_db";
    private static final String TAG = "ROOM TESTING";

    private AppDatabase appDatabase;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private EditText etMovie;
    private RecyclerView rvMovies;
    private ProgressBar pbLoading;
    private TextView tvLabel;

    private List<Movies> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initDrawer();
        initViews();
        initList();

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        fetchMovies();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        Button btnAddMovie = findViewById(R.id.btn_add);
        Button btnShow = findViewById(R.id.btn_show);
        FloatingActionButton fab = findViewById(R.id.fab);
        btnAddMovie.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        fab.setOnClickListener(this);

        etMovie = findViewById(R.id.et_movies);
        rvMovies = findViewById(R.id.rv_movies);
        pbLoading = findViewById(R.id.pb_loading);
        tvLabel = findViewById(R.id.tv_label);
    }

    private void initList() {

        moviesList = new ArrayList<>();

        Log.d(TAG, "Testing");

        MoviesListAdapter adapter = new MoviesListAdapter(moviesList);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(adapter);
    }

    private void finishedSaving(Movies movie) {
        Toast.makeText(this, "Movie saved!", Toast.LENGTH_LONG).show();
        moviesList.add(movie);
        rvMovies.getAdapter().notifyDataSetChanged();
        etMovie.setText("");
        tvLabel.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etMovie.getWindowToken(), 0);
    }

    private void displayMovies(List<Movies> listMovies) {
        moviesList.clear();
        moviesList.addAll(listMovies);
        rvMovies.getAdapter().notifyDataSetChanged();
        Toast.makeText(this, "Movies length: " + listMovies.size(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
//                addMovie();
                saveMovie();
                break;
            case R.id.btn_show:
//                showLocal();
                fetchMovies();
                break;
            case R.id.fab:
                showSnackBar(v, "Replace with action");
                break;
        }
    }

    private void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    private void fetchMovies() {
        Observable.fromCallable(() -> appDatabase.movieDao().fetchAllMovies())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> displayMovies(result));
    }

    private void saveMovie() {
        String movie = etMovie.getText().toString();
        if (movie.length() > 0) {
            Movies movies = new Movies(generateId(), movie);

            Observable.fromCallable(() -> {
                appDatabase.movieDao().insertOnlySingleMovie(movies);
                return movies;
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((result) -> finishedSaving(result));
        }
    }

    private String generateId() {
        return System.currentTimeMillis() + "";
    }
}
