package com.dch.perfect.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by dch on 2018/1/19.
 */

public final class Perfect {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getPerfectConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
