package ru.company.moviesmvvm.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieViewModelFactory implements ViewModelProvider.Factory {
    private String apiKey;
    private String searchText;

    public MovieViewModelFactory(String apiKey, String searchText){
        this.apiKey = apiKey;
        this.searchText = searchText;
    }


    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel();
    }
}
