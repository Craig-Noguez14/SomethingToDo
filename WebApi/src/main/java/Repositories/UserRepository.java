package Repositories;

import Interfaces.IUserService;

public class UserRepository {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    private static final String URL = "http://192.168.0.12:8080/api";
    private retrofit.RestAdapter restAdapter;
    private IUserService apiService;

    public UserRepository()
    {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(IUserService.class);
    }

    public IUserService getService()
    {
        return apiService;
    }
}
