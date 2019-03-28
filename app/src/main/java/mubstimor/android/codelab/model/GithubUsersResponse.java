package mubstimor.android.codelab.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GithubUsersResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("items")
    private ArrayList<GithubUser> githubUserList;

    public ArrayList<GithubUser> getGithubUserList() {
        return githubUserList;
    }

    public int getTotalCount(){
        return totalCount;
    }

}