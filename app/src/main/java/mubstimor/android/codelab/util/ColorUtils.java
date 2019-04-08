package mubstimor.android.codelab.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import mubstimor.android.codelab.R;

/**
 * This class alternates the color of rows in the list view.
 * @author Timothy Mubiru
 */

public class ColorUtils {

    /**
     * changes background for alternating rows.
     * @param context class calling the method
     * @param instanceNum id of row calling method
     * @return updates color of row
     */
    public int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        if (instanceNum % 2 == 0) {
            return ContextCompat.getColor(context, R.color.white);
        } else {
            return ContextCompat.getColor(context, R.color.grey);
        }
    }
}
