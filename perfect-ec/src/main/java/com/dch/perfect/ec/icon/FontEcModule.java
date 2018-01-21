package com.dch.perfect.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by dch on 2018/1/19.
 */

public class FontEcModule  implements IconFontDescriptor{
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
