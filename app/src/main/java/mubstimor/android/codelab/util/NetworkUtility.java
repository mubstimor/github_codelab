package mubstimor.android.codelab.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class implements a utility class to check for a network connection.
 */
public class NetworkUtility extends BroadcastReceiver {

    protected boolean isActive = false;
    private static NetworkListener networkListener;

    /**
     * get listener.
     * @return listener object
     */
    public static NetworkListener getNetworkListener() {
        return networkListener;
    }

    /**
     * set listener.
     * @param networkListener listener instance
     */
    public static void setNetworkListener(NetworkListener networkListener) {
        NetworkUtility.networkListener = networkListener;
    }

    /**
     * Default constructor for use in given situations.
     */
    public NetworkUtility() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        isActive = isOnline(context);

        if (networkListener != null) {
            networkListener.onNetworkStatusChange(isActive);
        }

    }

    /**
     * Check internet connection.
     * @param context app context
     * @return connection
     */
    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected();
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Pass the resulting data from the service to the activity.
     * using the NetworkListener interface
     */
    public interface NetworkListener {

        /**
         * pass results to activity.
         * @param isConnected status of network
         */
        void onNetworkStatusChange(boolean isConnected);
    }
}
