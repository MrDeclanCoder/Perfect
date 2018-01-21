package com.dch.perfect.core.mvp;

/**
 * Created by dch on 2018/1/21.
 */

public interface PresenterMvpFactory<V extends BaseMvpView,P extends BaseMvpPresenter> {

    /**
     * 创建presenter的接口方法
     * @return 需要创建的presenter
     */
    P createMvpPresenter();
}
