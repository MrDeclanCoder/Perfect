package com.dch.perfect.core.net.rx;

import android.content.Context;

import com.dch.perfect.core.net.RestClient;
import com.dch.perfect.core.net.RestCreator;
import com.dch.perfect.core.net.callback.IError;
import com.dch.perfect.core.net.callback.IFailure;
import com.dch.perfect.core.net.callback.IRequest;
import com.dch.perfect.core.net.callback.ISuccess;
import com.dch.perfect.core.ui.loading.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by dch on 2018/1/20.
 */

public class RxRestClientBuilder {
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;

    RxRestClientBuilder(){

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, String value){
        PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath){
        this.mFile = new File(filePath);
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=Utf-8"),raw);
        return this;
    }

    public final RxRestClientBuilder onRequest(IRequest iRequest){
        this.mRequest = iRequest;
        return this;
    }

    public final RxRestClientBuilder success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }

    public final RxRestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }

    public final RxRestClientBuilder error(IError iError){
        this.mIError = iError;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS,mBody,mFile,mLoaderStyle,mContext);
    }
}
