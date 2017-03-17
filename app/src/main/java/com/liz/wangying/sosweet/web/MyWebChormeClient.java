package com.liz.wangying.sosweet.web;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by Liz on 2017/3/15.
 * email: lizwangying@icloud.com
 */

public class MyWebChormeClient extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.d("haha",message);
        
        return true;
    }

}
