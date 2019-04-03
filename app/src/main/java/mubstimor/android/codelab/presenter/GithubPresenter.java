package mubstimor.android.codelab.presenter;

import java.util.List;

import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.model.GithubUsersResponse;
import mubstimor.android.codelab.service.GithubService;
import mubstimor.android.codelab.view.ProfileView;
import mubstimor.android.codelab.view.SearchView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class implements a Presenter that maps views to models.
 * @author Timothy Mubiru
 */

public class GithubPresenter {

    private GithubService githubService;

    /**
     * Initialises a service that connects to API endpoints.
     */
    public GithubPresenter() {
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    /**
     * get users from endpoint.
     * @param searchView view to which response is mapped
     */
    public void getGithubUsers(final SearchView searchView) {
        githubService
                .getAPI()
                .getSearchResults()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(
                            Call<GithubUsersResponse> call,
                            Response<GithubUsersResponse> response
                    ) {
                        GithubUsersResponse data = response.body();

                        if (data != null && data.getGithubUserList() != null) {
                            List<GithubUser> result = data.getGithubUserList();
                            int totalCount = data.getTotalCount();
                            searchView.usersReady(result, totalCount);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        throwError();
                    }
                });
    }

    /**
     * throw error on request failure.
     */
    void throwError() {
        try {
            throw new InterruptedException("Something went wrong!");
        } catch (InterruptedException e) { }
    }

    /**
     * Get profile from user detail API.
     * @param user username for user to retrieve
     * @param profileView view to which response is mapped
     */
    public void getUserProfile(String user, final ProfileView profileView) {
        githubService
                .getAPI()
                .getProfileOf(user)
                .enqueue(new Callback<GithubUser>() {
                    @Override
                    public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                        GithubUser data = response.body();

                        if (data != null) {
                            profileView.profileReady(data);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUser> call, Throwable t) {
                        throwError();
                    }
                });
    }
}
