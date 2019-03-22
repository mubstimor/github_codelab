package mubstimor.android.codelab;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

public class ColorUtils {


    public static int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        if (instanceNum % 2 == 0) {
            return ContextCompat.getColor(context, R.color.white);
        } else{
            return ContextCompat.getColor(context, R.color.grey);
        }
    }
}
