package com.dch.perfect.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.dch.perfect.core.app.Perfect;

/**
 * Created by dch on 2018/1/21.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Perfect.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Perfect.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
