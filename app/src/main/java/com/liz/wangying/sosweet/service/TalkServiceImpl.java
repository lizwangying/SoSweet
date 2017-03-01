package com.liz.wangying.sosweet.service;

import android.media.browse.MediaBrowser;
import android.os.Build;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import java.util.List;

/**
 * desc:
 * Created by Liz on 2017/2/28.
 * github: https://github.com/lizwangying
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TalkServiceImpl extends MediaBrowserService {
    @Nullable
    @Override
    public BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle rootHints) {
        return null;
    }

    @Override
    public void onLoadChildren(String parentId, Result<List<MediaBrowser.MediaItem>> result) {

    }
}
