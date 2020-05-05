package ru.company.moviesmvvm.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.company.moviesmvvm.model.SearchModel;

public class Movie {
    @SerializedName("Search")
    private List<SearchModel> search;

    public List<SearchModel> getSearches() {
        return search;
    }
}
