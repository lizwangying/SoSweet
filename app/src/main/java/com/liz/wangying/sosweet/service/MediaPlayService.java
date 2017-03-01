package com.liz.wangying.sosweet.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MediaPlayService extends Service {
    public MediaPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
