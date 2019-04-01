package mubstimor.android.codelab.view;

import java.util.ArrayList;

import mubstimor.android.codelab.model.GithubUser;

public interface SearchView {
    void usersReady(ArrayList<GithubUser> githubUsers, int totalCount);
}
