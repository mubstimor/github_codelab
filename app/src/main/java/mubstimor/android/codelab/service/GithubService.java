package mubstimor.android.codelab.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class creates a retrofit instance.
 * @author Timothy Mubiru
 */

public class GithubService {

    private Retrofit retrofit = null;

    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    public GithubApi getAPI() {
        String baseURL = "https://api.github.com/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(GithubApi.class);
    }
}
