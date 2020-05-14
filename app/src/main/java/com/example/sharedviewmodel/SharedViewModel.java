package com.example.sharedviewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private static final String TAG = "SharedViewModel";
    private MutableLiveData<List<Movie>> mutableMovieList = new MutableLiveData<>();
    private MutableLiveData<Movie> mutableMovie = new MutableLiveData<>();

    public LiveData<List<Movie>> getMovieList() {
        if (mutableMovieList.getValue() == null) {
            new LoadMovies().execute();
        }
        return mutableMovieList;
    }

    public LiveData<Movie> getMovie() {
        return mutableMovie;
    }

    public void setMovie(int position) {
        Movie movie = mutableMovieList.getValue().get(position);
        mutableMovie.setValue(movie);
    }

    public void addMovie(Movie movie) {
        if (mutableMovieList.getValue() != null) {
            List<Movie> movieList = mutableMovieList.getValue();
            movieList.add(movie);
            mutableMovieList.setValue(movieList);
            return;
        }
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        mutableMovieList.setValue(movieList);
    }

    class LoadMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            List<Movie> movieList = new ArrayList<>();
            movieList.add(new Movie( "Captain America", 8.8));
            movieList.add(new Movie( "Iron Man", 7.8));
            movieList.add(new Movie("Avengers", 6.8));
            mutableMovieList.postValue(movieList);
        }
    }
}
