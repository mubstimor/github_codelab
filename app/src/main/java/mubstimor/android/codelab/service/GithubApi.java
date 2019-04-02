package mubstimor.android.codelab.service;

import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.model.GithubUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This interface defines methods and paths to query the API.
 * @author Timothy Mubiru
 */

public interface GithubApi {

    /**
     * search github users.
     * @return json containing users from API
     */
    @GET("/search/users?q=language:java+location:Kenya&type=Users")
    Call<GithubUsersResponse> getSearchResults();

    /**
     * get user of given profile.
     * @param user profile to retrieve
     * @return json containing user details
     */
    @GET("/users/{user}")
    Call<GithubUser> getProfileOf(@Path("user") String user);
}
