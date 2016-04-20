/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by elpark on 2/9/15.
 */
public class App extends Application {

    public static String TAG = App.class.getCanonicalName();
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        ActiveAndroid.initialize(this);
    }

    public static App getApp() {
        return app;
    }
}
