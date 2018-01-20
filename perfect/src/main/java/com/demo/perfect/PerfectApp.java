package com.demo.perfect;

import android.app.Application;

import com.dch.perfect.core.app.Perfect;
import com.dch.perfect.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by dch on 2018/1/19.
 */

public class PerfectApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Perfect.init(getApplicationContext())
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://www.demo.com")
                .configure();
    }
}
