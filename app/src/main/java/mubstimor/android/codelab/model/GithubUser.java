package mubstimor.android.codelab.model;

import com.google.gson.annotations.SerializedName;

public class GithubUser {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String name;

    private String location;

    private String company;

    private String bio;

    @SerializedName("html_url")
    private String htmlUrl;


    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getBio() {
        return bio;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}
