package com.deerplayer.net.service;


import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ImageService {

    @GET("res/resVersion")
    Observable<ResponseBody> getVersion(@QueryMap HashMap<String, Object> allMap);
}
