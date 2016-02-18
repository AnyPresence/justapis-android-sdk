package com.anypresence.gw;


import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

abstract public class APOkHttpCallback<T> extends com.anypresence.gw.APCallback<T> implements Callback {
    public RequestContext<T> createRequestContext(HTTPMethod method, String url, APAndroidGateway gateway) {
        return null;
    }

    public abstract T transformResponse(Response response) throws IOException;

    @Override
    public void onFailure(Request request, IOException e) {
        finished(null, e);
    }

    @Override
    public void onResponse(Response response) {
        T parsedResponse;
        try {
            parsedResponse = transformResponse(response);
            finished(parsedResponse, null);
        } catch (IOException e) {
            finished(null, e);
        }


    }

}
