package Interfaces;

import Models.Event;
import Models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import io.reactivex.Observable;

public interface IEventService {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @POST("event/PostEvent")
    public Observable<ResponseBody> CreateEvent(@Body Event event);
}
