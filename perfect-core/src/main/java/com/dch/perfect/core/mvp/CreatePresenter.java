package com.dch.perfect.core.mvp;

import java.lang.annotation.Inherited;

/**
 * Created by dch on 2018/1/21.
 */
@Inherited
public @interface CreatePresenter {

    Class<? extends BaseMvpPresenter> value();
}
