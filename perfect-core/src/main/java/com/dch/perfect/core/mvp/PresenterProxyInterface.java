package com.dch.perfect.core.mvp;

/**
 * Created by dch on 2018/1/21.
 * 代理接口
 */

public interface PresenterProxyInterface<V extends BaseMvpView, P extends BaseMvpPresenter> {

    /**
     * 设置创建presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterMvpFactory<V,P> presenterFactory);

    /**
     * 获取presenter的工厂
     * @return PresenterMvpPresenter工厂类型
     */
    PresenterMvpFactory<V,P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     * @return 指定类型的presenter
     */
    P getMvpPresenter();
}
