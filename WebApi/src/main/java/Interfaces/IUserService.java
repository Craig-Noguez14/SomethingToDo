package Interfaces;

import Models.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface IUserService {

    @GET("/user/{id}")
    public void GetUserById(@Path("id") Integer id,Callback<User> callback);

    //i.e. http://localhost/api/institute/Students
    //Add student record and post content in HTTP request BODY
    @POST("/user/save")
    public void AddUser(@Body User user,Callback<User> callback);
}
