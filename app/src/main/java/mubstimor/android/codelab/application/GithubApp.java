package mubstimor.android.codelab.application;

import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import mubstimor.android.codelab.util.NetworkUtility;

/**
 * This Class allows access to all activities within the app's lifecycle.
 */

public class GithubApp extends Application {

    MyActivityLifecycleCallbacks mCallbacks;
    NetworkUtility mNetworkReceiver;

    private final GithubApp mInstance;

    /**
     * set app instance.
     */
    public GithubApp() {
        mInstance = this;
    }

    /**
     * Get instance.
     * @return app instance
     */
    public GithubApp getmInstance() {
        return mInstance;
    }

    @SuppressWarnings("MethodLength")
    @Override
    public void onCreate() {
        super.onCreate();
        mCallbacks = new MyActivityLifecycleCallbacks();
        registerActivityLifecycleCallbacks(mCallbacks);
    }

    /**
     * register service for builds later than Nougat.
     */
    void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(
                    ConnectivityManager.CONNECTIVITY_ACTION)
            );
        }
    }

    /**
     * set listener globally.
     *
     * @param listener instance
     */
    public void setNetworkListener(NetworkUtility.NetworkListener listener) {
        NetworkUtility.setNetworkListener(listener);
    }

    /**
     * This class implements activity lifecyle callback methods.
     */
    public class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        Activity mActivity;

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            mNetworkReceiver = new NetworkUtility();
        }

        @Override
        public void onActivityStarted(Activity activity) {
            mActivity = activity;
        }

        @Override
        public void onActivityResumed(Activity activity) {
            mActivity = activity;
            registerNetworkBroadcastForNougat();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            mActivity = null;
            unregisterReceiver(mNetworkReceiver);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            // intentionally left blank.
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            // intentionally left blank.
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            // intentionally left blank.
        }
    }

}
