package mubstimor.android.codelab.model;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import mubstimor.android.codelab.model.GithubUser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class GithubUserTest {

    @Test
    public void test_user_is_parcelable() {
        GithubUser githubUser = new GithubUser("k33ptoo", "https://avatars0.githubusercontent.com/u/6637970?v=4");

        Parcel parcel = Parcel.obtain();
        githubUser.writeToParcel(parcel, githubUser.describeContents());
        parcel.setDataPosition(0);

        GithubUser createdFromParcel = (GithubUser) GithubUser.CREATOR.createFromParcel(parcel);
        assertThat(createdFromParcel.getUsername(), is("k33ptoo"));
        assertThat(createdFromParcel.getAvatarUrl(), is("https://avatars0.githubusercontent.com/u/6637970?v=4"));
    }

    @Test
    public void getUsername() {
    }

    @Test
    public void getAvatarUrl() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getLocation() {
    }

    @Test
    public void getCompany() {
    }

    @Test
    public void getBio() {
    }

    @Test
    public void getHtmlUrl() {
    }

    @Test
    public void setUsername() {
    }

    @Test
    public void setAvatarUrl() {
    }

    @Test
    public void setName() {
    }

    @Test
    public void setLocation() {
    }

    @Test
    public void setCompany() {
    }

    @Test
    public void setBio() {
    }

    @Test
    public void setHtmlUrl() {
    }

    @Test
    public void describeContents() {
    }

    @Test
    public void writeToParcel() {
    }
}