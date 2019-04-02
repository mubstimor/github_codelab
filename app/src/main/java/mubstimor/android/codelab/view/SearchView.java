package mubstimor.android.codelab.view;

import java.util.List;

import mubstimor.android.codelab.model.GithubUser;

/**
 * This interface maps search response to the main layout.
 * @author Timothy Mubiru
 */

public interface SearchView {

    /**
     * map search response to main layout.
     * @param githubUsers list of github users
     * @param totalCount total number of users from search
     */
    void usersReady(List<GithubUser> githubUsers, int totalCount);

}
