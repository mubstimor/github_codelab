package mubstimor.android.codelab.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class implements a model for GithubUsers response.
 * @author Timothy Mubiru
 */

public class GithubUsersResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("items")
    private List<GithubUser> githubUserList;

    /**
     * @return githubUserList list of Github users
     */
    public List<GithubUser> getGithubUserList() {
        return githubUserList;
    }

    /**
     * @return totalCount total number from search
     */
    public int getTotalCount() {
        return totalCount;
    }
}
