package com.liz.wangying.sosweet.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import java.util.zip.Checksum;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TalkService extends Service implements Checksum{
    MediaSession mediaSession;


    public TalkService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void update(int b) {

    }

    @Override
    public void update(byte[] b, int off, int len) {

    }

    @Override
    public long getValue() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
