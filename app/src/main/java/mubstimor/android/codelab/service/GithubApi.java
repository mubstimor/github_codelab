package mubstimor.android.codelab.service;

import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.model.GithubUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("/search/users?q=language:java+location:Kenya&type=Users")
    Call<GithubUsersResponse> getSearchResults();

    @GET("/users/{user}")
    Call<GithubUser> getProfileOf(@Path("user") String user);
}