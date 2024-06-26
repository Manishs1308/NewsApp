package com.example.newsapp.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.model.MResponse;
import com.example.newsapp.repo.SourceRepo;

public class MainActivityViewModel extends AndroidViewModel {

    private final SourceRepo sourceRepo;

    public MainActivityViewModel(@NonNull Application application){
        super(application);
    sourceRepo = SourceRepo.getSourceRepo();
    }

    public LiveData<MResponse> sourceLiveData() {
        return sourceRepo.getSource();
    }
}
