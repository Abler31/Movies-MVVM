package ru.company.moviesmvvm.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.company.moviesmvvm.adapter.MovieAdapter;
import ru.company.moviesmvvm.model.SearchModel;
import ru.company.moviesmvvm.response.Movie;
import ru.company.moviesmvvm.retrofit.JsonPlaceHolderApi;
import ru.company.moviesmvvm.retrofit.RetrofitRequest;

public class MovieRepository {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private static MovieRepository moviesRepository;
    private MutableLiveData<Movie> data;

    public static MovieRepository getInstance(){
        if (moviesRepository == null){
            moviesRepository = new MovieRepository();
        }
        return moviesRepository;
    }

    public MovieRepository(){
        jsonPlaceHolderApi = RetrofitRequest.getRetrofitInstance().create(JsonPlaceHolderApi.class);
    }

    public MutableLiveData<Movie> getMovies(String apiKey, String searchText){
        data = new MutableLiveData<>();
        jsonPlaceHolderApi.getMovies(apiKey, searchText)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.body()!=null){
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
