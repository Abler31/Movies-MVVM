package ru.company.moviesmvvm.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.company.moviesmvvm.response.Movie;

public interface JsonPlaceHolderApi {

    @GET(".")
    Call<Movie> getMovies(
            @Query("apikey") String apiKey,
            @Query("s") String search
    );
}
