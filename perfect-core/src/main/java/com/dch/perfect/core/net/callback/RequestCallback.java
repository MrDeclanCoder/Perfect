package com.dch.perfect.core.net.callback;

import android.os.Handler;

import com.dch.perfect.core.ui.loading.LoaderStyle;
import com.dch.perfect.core.ui.loading.PerfectLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dch on 2018/1/20.
 */

public class RequestCallback implements Callback{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallback(IRequest request, ISuccess success, IFailure failure, IError iError,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = iError;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR!= null){
                ERROR.onError(response.code(),response.message());
            }
        }
        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    PerfectLoader.stopLoading();
                }
            },1000);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (FAILURE != null){
           FAILURE.onFailure();
        }
        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }
}
