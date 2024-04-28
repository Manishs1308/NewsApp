package com.example.newsapp.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.model.MResponse;
import com.example.newsapp.model.MSource;
import com.example.newsapp.retrofit.API;
import com.example.newsapp.retrofit.ApiClient;
import com.example.newsapp.ui.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceRepo {

    private final MutableLiveData<MResponse> mutableLiveData = new MutableLiveData<>();
    private List<MSource> sourceList = new ArrayList<>();

    private static SourceRepo sourceRepo = null;

    public static SourceRepo getSourceRepo() {
        if (sourceRepo == null)
            sourceRepo = new SourceRepo();
        return sourceRepo;
    }

    public MutableLiveData<MResponse> getSource() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(API.API_KEY, Constant.API_KEY);
        map.put(API.COUNTRY, Constant.COUNTRY);
        map.put(API.CATEGORY,Constant.CATEGORY);
        Call<JsonElement> call = ApiClient.getRetrofitService().getSources(map);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                MResponse mResponse = new MResponse();
                if (response.isSuccessful()) {
                    sourceList.clear();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        Type type = new TypeToken<ArrayList<MSource>>() {
                        }.getType();
                        sourceList = new Gson().fromJson(jsonObject.getJSONArray(API.SOURCES).toString(), type);
                        mResponse.setSourceList(sourceList);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mutableLiveData.setValue(mResponse);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
