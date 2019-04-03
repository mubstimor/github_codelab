package mubstimor.android.codelab.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This class implements a model for GithubUser.
 * @author Timothy Mubiru
 */

public class GithubUser implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public GithubUser createFromParcel(Parcel in) {
            return new GithubUser(in);
        }

        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };

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
     * constructor for Github list item.
     * @param username user handle
     * @param avatarUrl profile image
     */
    public GithubUser(String username, String avatarUrl) {
        this.username = username;
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

     /** Parcelling part.
     * @param in parcel object
     */
    public GithubUser(Parcel in) {
        this.username = in.readString();
        this.avatarUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.avatarUrl);
    }
}
