package hotwire.com.mvp.sample.utilities;

import android.util.Log;

/**
 * Created by elpark on 2/9/15.
 */
public class Logger {

    public static void debugLog(String message) {
        Log.d("debug", message);
    }

    public static void errorLog(String message) {
        Log.e("debug", message);
    }
}
