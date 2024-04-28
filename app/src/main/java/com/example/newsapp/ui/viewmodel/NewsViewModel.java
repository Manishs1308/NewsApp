package com.example.newsapp.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapp.model.MResponse;
import com.example.newsapp.repo.ArticleRepo;
import com.example.newsapp.ui.utils.MyLoader;

public class NewsViewModel extends AndroidViewModel {

    private final ArticleRepo articleRepo;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        articleRepo = ArticleRepo.getArticleRepo();
    }

    public LiveData<MResponse> articleLiveData(String source, MyLoader loader) {
        return articleRepo.getArticles(source, loader);
    }
}
