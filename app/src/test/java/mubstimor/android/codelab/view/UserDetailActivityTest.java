package mubstimor.android.codelab.view;

import android.content.Intent;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import mubstimor.android.codelab.R;
import mubstimor.android.codelab.application.GithubApp;
import mubstimor.android.codelab.model.GithubUser;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(application = GithubApp.class)
public class UserDetailActivityTest {

    private UserDetailActivity activity;
    private final String USERNAME = "k33ptoo";
    private final String PROFILEIMAGE = "https://avatars0.githubusercontent.com/u/6637970?v=4";

    @Before
    public void setup() {
        GithubUser githubUser = new GithubUser(USERNAME, PROFILEIMAGE);
        Intent intent = new Intent();
        intent.putExtra("githubUser", githubUser);
        activity = Robolectric.buildActivity(UserDetailActivity.class, intent).create().get();
    }

    @Test
    public void titleIsCorrect() throws Exception {
        assertTrue(activity.getTitle().toString().equals("Details"));
    }

    @Test
    public void shareButtonClick_returnsChooser() {
        ImageView shareIconView = (ImageView) activity.findViewById(R.id.iv_share);
        shareIconView.performClick();
        ShadowActivity shadowActivity = shadowOf(activity);
        assertEquals(shadowActivity.getNextStartedActivity().getAction(), Intent.ACTION_CHOOSER);
    }

}