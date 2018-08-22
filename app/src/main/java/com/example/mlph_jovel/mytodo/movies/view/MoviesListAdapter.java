package com.example.mlph_jovel.mytodo.movies.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mlph_jovel.mytodo.R;
import com.example.mlph_jovel.mytodo.movies.model.Movies;

import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder>{

    private List<Movies> movieList;

    public MoviesListAdapter(List<Movies> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        final View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_movies, viewGroup, false);
        final ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Movies movies = movieList.get(i);
        viewHolder.tvId.setText(movies.getMovieId());
        viewHolder.tvTitle.setText(movies.getMovieName());
    }

}
