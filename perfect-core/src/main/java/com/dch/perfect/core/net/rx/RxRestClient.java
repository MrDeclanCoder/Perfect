package com.dch.perfect.core.net.rx;

import android.content.Context;

import com.dch.perfect.core.net.HttpMethod;
import com.dch.perfect.core.net.RestClientBuilder;
import com.dch.perfect.core.net.RestCreator;
import com.dch.perfect.core.net.RestService;
import com.dch.perfect.core.net.callback.IError;
import com.dch.perfect.core.net.callback.IFailure;
import com.dch.perfect.core.net.callback.IRequest;
import com.dch.perfect.core.net.callback.ISuccess;
import com.dch.perfect.core.net.callback.RequestCallback;
import com.dch.perfect.core.ui.loading.LoaderStyle;
import com.dch.perfect.core.ui.loading.PerfectLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by dch on 2018/1/20.
 */
//构造者模式
public class RxRestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        LoaderStyle loader_style,
                        Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loader_style;
        this.CONTEXT = context;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        if (LOADER_STYLE != null) {
            PerfectLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                observable = RestCreator.getRxRestService().upload(URL,body);
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String>  get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String>  post() {
        if (BODY == null){
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public Observable<String>  put() {
        if (BODY == null){
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }

    }

    public Observable<String>  delete() {
        return request(HttpMethod.DELETE);
    }

//    public final void upload() {
//        request(HttpMethod.UPLOAD);
//    }
//
    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;
    }
}
