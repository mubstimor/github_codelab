package mubstimor.android.codelab.presenter;

import android.util.Log;

import java.util.ArrayList;

import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.model.GithubUsersResponse;
import mubstimor.android.codelab.service.GithubService;
import mubstimor.android.codelab.view.ProfileView;
import mubstimor.android.codelab.view.SearchView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    private GithubService githubService;

    public GithubPresenter() {
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public void getGithubUsers(final SearchView searchView) {
        githubService
                .getAPI()
                .getSearchResults()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        GithubUsersResponse data = response.body();

                        if (data != null && data.getGithubUserList() != null) {
                            ArrayList<GithubUser> result = data.getGithubUserList();
                            int totalCount = data.getTotalCount();
                            searchView.usersReady(result, totalCount);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

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
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}