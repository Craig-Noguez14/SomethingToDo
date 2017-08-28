package Repositories;

import Interfaces.ILocationService;
import Interfaces.IUserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Craig on 8/20/2017.
 */

public class LocationRepository {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    private static final String URL = "https://maps.googleapis.com/";

    private ILocationService apiService;

    public LocationRepository()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ILocationService.class);
    }

    public ILocationService getService()
    {
        return apiService;
    }
}
