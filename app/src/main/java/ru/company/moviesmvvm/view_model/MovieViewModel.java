package ru.company.moviesmvvm.view_model;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.company.moviesmvvm.repository.MovieRepository;
import ru.company.moviesmvvm.response.Movie;

public class MovieViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<Movie> movieLiveData;

    public void init() {
        if (movieLiveData != null){
            return;
        }
        movieRepository = MovieRepository.getInstance();

    }
    public void getMoviesSearch(String apiKey, String searchText){
        movieLiveData = movieRepository.getMovies(apiKey, searchText);
    }

    public LiveData<Movie> getMovieLiveData(){
        return movieLiveData;
    }
}
