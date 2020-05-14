package com.example.sharedviewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<Movie> movieList;
    OnItemCLickListener onItemCLickListener;

    public MovieAdapter(List<Movie> movieList, OnItemCLickListener onItemCLickListener) {
        this.movieList = movieList;
        this.onItemCLickListener = onItemCLickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView ratingTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemCLickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        private void bind(Movie movie) {
            nameTextView.setText(movie.getName());
            ratingTextView.setText(String.valueOf(movie.getRating()));
        }
    }

    interface OnItemCLickListener {
        public void onItemClick(int position);
    }
}
