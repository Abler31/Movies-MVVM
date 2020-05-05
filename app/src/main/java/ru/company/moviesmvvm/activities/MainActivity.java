package ru.company.moviesmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.company.moviesmvvm.adapter.MovieAdapter;
import ru.company.moviesmvvm.R;
import ru.company.moviesmvvm.retrofit.JsonPlaceHolderApi;
import ru.company.moviesmvvm.response.Movie;
import ru.company.moviesmvvm.model.SearchModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.et_movieSearch);
        searchButton = findViewById(R.id.searchButton);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchEditText.getText().toString();

                getMovies(searchText);
            }
        });

    }

    private void getMovies(String searchText) {

        Call<Movie> call = jsonPlaceHolderApi.getMovies("1d616bee", searchText);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Movie movie = response.body();
                List<SearchModel> searchModels = movie.getSearches();

                MovieAdapter adapter = new MovieAdapter(searchModels, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
