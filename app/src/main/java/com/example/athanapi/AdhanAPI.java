package com.example.athanapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AdhanAPI {
    @GET("timingsByAddress")
    Call<PrayerTime> getPrayerTime(@Query("address") String location);
}
