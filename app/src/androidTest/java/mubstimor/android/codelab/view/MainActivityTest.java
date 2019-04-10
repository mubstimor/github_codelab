package mubstimor.android.codelab.view;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.v4.util.Preconditions.checkArgument;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mubstimor.android.codelab.R;
import mubstimor.android.codelab.util.EspressoIdlingResource;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private CountingIdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
//        mIdlingResource = mActivityTestRule.getActivity().getCountingIdlingResource();
//        IdlingRegistry.getInstance().register(mIdlingResource);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

    }

    @Test
    public void totalCount_displays() {
        onView(withId(R.id.tv_total_users)).check(matches(isDisplayed()));
    }

    /**
     * Clicks on an item and opens up the UserDetailActivity with the correct details.
     */
    @Test
    public void clickUserItem_OpensUserDetailActivity() {

        onView(ViewMatchers.withId(R.id.rv_gitusers))
                .perform(actionOnItemAtPosition(0,
                        click()));

        // Checks that the UserDetailActivity is opened
        onView(withId(R.id.tv_username)).check(matches(isDisplayed()));
    }

    @Test
    public void swipeToRefresh_getsData() {
        onView(withId(R.id.swiperefresh)).perform(swipeDown());
        onView(withId(R.id.swiperefresh)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_gitusers)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
//        if (mIdlingResource != null) {
//            IdlingRegistry.getInstance().unregister(mIdlingResource);
//        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

}
