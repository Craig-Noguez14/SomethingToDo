package Repositories;

import Interfaces.IEventService;
import Interfaces.IUserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Craig on 8/19/2017.
 */

public class EventRepository {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    private static final String URL = "http://192.168.0.12:8080/api/";
    private IEventService apiService;

    public EventRepository()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(IEventService.class);
    }

    public IEventService getService()
    {
        return apiService;
    }
}
