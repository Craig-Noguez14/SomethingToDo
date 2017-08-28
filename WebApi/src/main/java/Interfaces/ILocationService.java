package Interfaces;

import Models.Event;
import Models.GeoCodeLocation;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Craig on 8/20/2017.
 */

public interface ILocationService {
    @GET("maps/api/geocode/json")
    public retrofit2.Call<GeoCodeLocation> getCityResults(@Query("place_id") String placeId, @Query("key") String key);
}
