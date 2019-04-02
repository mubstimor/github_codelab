package mubstimor.android.codelab.model;

import com.google.gson.annotations.SerializedName;

/**
 * This class implements a model for GithubUser.
 * @author Timothy Mubiru
 */

public class GithubUser {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String name;

    private String location;

    private String company;

    private String bio;

    /**
     * @param serialize htmlUrl to match response
     * */
    @SerializedName("html_url")
    private String htmlUrl;

    /**
     * @return String - username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return String - avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @return String - name
     */
    public String getName() {
        return name;
    }

    /**
     * @return String - location
     * */
    public String getLocation() {
        return location;
    }

    /**
     * @return String - company
     * */
    public String getCompany() {
        return company;
    }

    /**
     * @return String - bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @return String - htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * set username.
     * @param username handle of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * set image.
     * @param avatarUrl profile image
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * set full name.
     * @param name full name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set location.
     * @param location city of user
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * set company.
     * @param company user organisation
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * set bio.
     * @param bio user bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * set web link.
     * @param htmlUrl web address for profile
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
