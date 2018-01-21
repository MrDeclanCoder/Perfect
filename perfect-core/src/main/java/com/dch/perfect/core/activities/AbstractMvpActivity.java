package com.dch.perfect.core.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.dch.perfect.core.R;
import com.dch.perfect.core.delegates.PerfectDelegate;
import com.dch.perfect.core.mvp.BaseMvpPresenter;
import com.dch.perfect.core.mvp.BaseMvpProxy;
import com.dch.perfect.core.mvp.BaseMvpView;
import com.dch.perfect.core.mvp.PresenterMvpFactory;
import com.dch.perfect.core.mvp.PresenterMvpFactoryImpl;
import com.dch.perfect.core.mvp.PresenterProxyInterface;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by dch on 2018/1/21.
 */

public abstract class AbstractMvpActivity<V extends BaseMvpView,P extends BaseMvpPresenter<V>> extends SupportActivity implements PresenterProxyInterface<V,P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    public abstract PerfectDelegate setRootDelegate();
    /**
     * 创建被代理对象,传入默认presenter的工厂
     */
    private BaseMvpProxy<V,P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }


    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}
