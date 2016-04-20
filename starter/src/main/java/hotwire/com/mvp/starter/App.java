package hotwire.com.mvp.starter;

import com.activeandroid.ActiveAndroid;

/**
 * Created by elpark on 6/12/15.
 */
public class App extends com.activeandroid.app.Application {

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
