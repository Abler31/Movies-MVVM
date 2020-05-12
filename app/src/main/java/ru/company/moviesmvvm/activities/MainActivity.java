package ru.company.moviesmvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
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
import ru.company.moviesmvvm.view_model.MovieViewModel;
import ru.company.moviesmvvm.view_model.MovieViewModelFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private MovieAdapter movieAdapter;
    private ArrayList<SearchModel> moviesList = new ArrayList<>();
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

    }

    private void initialization(){
        searchEditText = findViewById(R.id.et_movieSearch);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.hasFixedSize();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        movieAdapter = new MovieAdapter(moviesList, MainActivity.this);

        recyclerView.setAdapter(movieAdapter);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

    }


    public void onClickSearch(View view) {
        movieViewModel.init();
        String searchText = searchEditText.getText().toString();
        movieViewModel.getMoviesSearch("1d616bee", searchText);
        movieViewModel.getMovieLiveData().observe(MainActivity.this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null){
                    moviesList.clear();
                    List<SearchModel> searchModels = movie.getSearches();
                    moviesList.addAll(searchModels);
                    movieAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
