package com.dch.perfect.core.mvp;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by dch on 2018/1/21.
 */

public class BaseMvpProxy<V extends BaseMvpView, P extends BaseMvpPresenter<V>> implements PresenterProxyInterface {

    /**
     * 获取onSaveInstanceState中的bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";

    /**
     * presenter工厂类
     */
    private PresenterMvpFactory<V,P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttachView;

    public BaseMvpProxy(PresenterMvpFactory<V, P> presenterMvpFactory) {
        this.mFactory = presenterMvpFactory;
    }

    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getMvpPresenter()之前,
     * 如果presenter已经创建则不能在修改
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(PresenterMvpFactory presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresener()之前调用,如果presenter已经创建" +
                    "则不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    /**
     * 获取presenter的工厂类
     * @return PresenterMvpPresenter工厂类型
     */
    @Override
    public PresenterMvpFactory getPresenterFactory() {
        return mFactory;
    }

    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    @Override
    public BaseMvpPresenter getMvpPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createMvpPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        return mPresenter;
    }


    public void onResume(V mvpView){
        getMvpPresenter();
        Log.e("Perfect-mvp","Proxy onResuem");
        if (mPresenter != null && !mIsAttachView)  {
            mPresenter.onAttachMvpView(mvpView);
            mIsAttachView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView(){
        if (mPresenter!= null && mIsAttachView){
            mPresenter.onDetachMvpView();
            mIsAttachView = false;
        }
    }

    /**
     * 销毁presenter
     */
    public void onDestroy(){
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter.onDestroyPresenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     * @return Bundle,存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState(){
        Log.e("Perfect-mvp","proxy onSaveInstanceState()");
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复presenter
     * @param savedInstanceState
     */
    public void onRestoreInstanceState(Bundle savedInstanceState){
        mBundle = savedInstanceState;
    }
}
