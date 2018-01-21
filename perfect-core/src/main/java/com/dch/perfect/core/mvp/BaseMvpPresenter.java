package com.dch.perfect.core.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dch on 2018/1/21.
 * 所有presenter的基类,并不强制实现这些方法,有需要再重写
 */
public class BaseMvpPresenter<V extends BaseMvpView> {

    /**
     * V层view
     */
    private V mView;

    /**
     * Presenter被创建后调用
     * @param savedState 被意外销毁重建后Bundle
     */
    public void onCreatePresenter(@Nullable Bundle savedState){
        Log.e("perfect-mvp","P onCreatePresenter");
    }

    /**
     * 绑定View
     */
    public void onAttachMvpView(V mvpView){
        mView = mvpView;
        Log.e("perfect-mvp","P onAttachMvpView");
    }

    /**
     * 解绑View
     */
    public void onDetachMvpView(){
        mView = null;
        Log.e("perfect-mvp","P onDetachMvpView");
    }

    /**
     * presenter被销毁时调用
     */
    public void onDestroyPresenter(){
        Log.e("perfect-mvp","P onDestroyPresenter");
    }

    /**
     * 在presenter意外销毁的时候被调用,它的调用时机和Activity,Fragment,View中的onSaveInstanceState时机相同
     * @param outState 状态量
     */
    public void onSaveInstanceState(Bundle outState){
        Log.e("perfect-mvp","P onSaveInstanceState");
    }

    /**
     * 获取V层接口view
     * @return
     */
    public V getMvpView(){
        return mView;
    }

}
