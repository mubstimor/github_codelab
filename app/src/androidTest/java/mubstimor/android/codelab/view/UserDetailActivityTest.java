package mubstimor.android.codelab.view;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mubstimor.android.codelab.R;
import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.util.EspressoIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class UserDetailActivityTest {

    private CountingIdlingResource mIdlingResource;

    private final String USERNAME = "k33ptoo";
    private final String PROFILEIMAGE = "https://avatars0.githubusercontent.com/u/6637970?v=4";

    @Rule
    public ActivityTestRule<UserDetailActivity> detailActivityTestRule =
            new ActivityTestRule<UserDetailActivity>(UserDetailActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    GithubUser githubUser = new GithubUser(USERNAME, PROFILEIMAGE);
                    Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
                    Intent result = new Intent(targetContext, UserDetailActivity.class);
                    result.putExtra("githubUser", githubUser);
                    return result;
                }
            };

    /**
     * Register idling resource.
     */
    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void userDetailScreen_isRendered() {
        onView(withId(R.id.tv_username)).check(matches(withText(USERNAME)));
    }

    @Test
    public void updateUI() {
        try {
            onView(withId(R.id.tv_user_location)).check(matches(not(withText(""))));
        } catch (Exception e) {
            Log.i("UPDATE_UI", e.getMessage());
        }
    }

    @Test
    public void shareProfileIcon_isDisplayed() {
        onView(withId(R.id.iv_share)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

}