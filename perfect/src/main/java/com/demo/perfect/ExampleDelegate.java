package com.demo.perfect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dch.perfect.core.delegates.PerfectDelegate;
import com.dch.perfect.core.net.RestClient;
import com.dch.perfect.core.net.callback.IError;
import com.dch.perfect.core.net.callback.IFailure;
import com.dch.perfect.core.net.callback.ISuccess;

/**
 * Created by dch on 2018/1/20.
 */

public class ExampleDelegate extends PerfectDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void testRestClient(){
        RestClient.builder()
                .url("")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(Object response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build();
    }
}
