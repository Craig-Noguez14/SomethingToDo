package Interfaces;

import android.telecom.Call;

import Models.User;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUserService {

    @GET("user/{email}/")
    public retrofit2.Call<User> GetUserByEmail(@Path("email") String email);

    //i.e. http://localhost/api/institute/Students
    //Add student record and post content in HTTP request BODY
    @POST("user/save")
    public retrofit2.Call<ResponseBody> AddUser(@Body User user);
}
