package Interfaces;

import Models.Event;
import Models.User;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IEventService {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @POST("event/PostEvent")
    public retrofit2.Call<ResponseBody> CreateEvent(@Body Event event);
}
