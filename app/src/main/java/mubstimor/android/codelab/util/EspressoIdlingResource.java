package mubstimor.android.codelab.util;

import android.support.test.espresso.IdlingResource;

/**
 * Contains a static reference IdlingResource.
 */
public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static SimpleCountingIdlingResource mCountingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    /**
     * Increment idling status.
     */
    public void increment() {
        mCountingIdlingResource.increment();
    }

    /**
     * decrement idling resource.
     */
    public void decrement() {
        mCountingIdlingResource.decrement();
    }

    /**
     * Get resource.
     * @return idling status
     */
    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }
}
