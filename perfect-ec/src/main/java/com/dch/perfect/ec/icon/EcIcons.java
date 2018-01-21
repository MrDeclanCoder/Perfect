package com.dch.perfect.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by dch on 2018/1/19.
 */

public enum EcIcons implements Icon{
    icon_san('\ue606'),
    icon_ali_pay('\ue606');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
