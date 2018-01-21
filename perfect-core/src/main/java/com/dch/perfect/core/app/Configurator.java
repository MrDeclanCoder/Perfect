package com.dch.perfect.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by dch on 2018/1/19.
 */

public class Configurator {

    private static final HashMap<String,Object> PERFECT_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator(){
        PERFECT_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String, Object> getPerfectConfigs(){
        return PERFECT_CONFIGS;
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        initIcons();
        PERFECT_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host){
        PERFECT_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) PERFECT_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("Unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) PERFECT_CONFIGS.get(key);
    }
}
