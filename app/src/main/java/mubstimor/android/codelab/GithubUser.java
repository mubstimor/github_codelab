package mubstimor.android.codelab;

import java.util.ArrayList;

public class GithubUser {

    private String username;
    private String imageUrl;
    private ArrayList<GithubUser> githubUsers = new ArrayList<>();

    public GithubUser() {
    }

    public GithubUser(String user, String image) {
        this.username = user;
        this.imageUrl = image;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
