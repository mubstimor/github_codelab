package mubstimor.android.codelab.view;

import mubstimor.android.codelab.model.GithubUser;

/**
 * This interface maps profile response to interface.
 * @author Timothy Mubiru
 */

public interface ProfileView {

    /**
     * Map user model to interface.
     * @param githubUser profile object of user
     */
    void profileReady(GithubUser githubUser);

}
