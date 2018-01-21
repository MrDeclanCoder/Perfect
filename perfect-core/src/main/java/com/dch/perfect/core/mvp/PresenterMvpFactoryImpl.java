package com.dch.perfect.core.mvp;

/**
 * Created by dch on 2018/1/21.
 */

public class PresenterMvpFactoryImpl<V extends BaseMvpView,P extends BaseMvpPresenter<V>> implements PresenterMvpFactory<V,P> {

    /**
     * 需要创建的presenter类型
     */
    private final Class<P> mPresenterClass;

    public static <V extends BaseMvpView,P extends BaseMvpPresenter<V>> PresenterMvpFactoryImpl<V,P>
    createFactory(Class<?> viewClazz){
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        }catch (Exception e){
            throw new RuntimeException("Presenter创建失败! 检查是否升平乐@CreatePresenter(xx.class)注解!");
        }
    }
}
