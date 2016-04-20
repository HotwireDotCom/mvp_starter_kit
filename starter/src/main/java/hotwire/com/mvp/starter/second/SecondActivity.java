package hotwire.com.mvp.starter.second;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import hotwire.com.mvp.R;
import hotwire.com.mvp.starter.second.view.SecondFragment;


/**
 * Created by elpark on 6/19/15.
 */
public class SecondActivity extends ActionBarActivity implements ISecondNavigator {

    public static String TAG = SecondActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        launchSecondFragment();
    }

    @Override
    public void launchSecondFragment() {
        SecondFragment fragment = (SecondFragment) getSupportFragmentManager()
                .findFragmentByTag(SecondFragment.TAG);
        if (null == fragment) {
            fragment = new SecondFragment();
        }
        if (!fragment.isAdded()  && !fragment.isRemoving() && !fragment.isVisible()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, SecondFragment.TAG)
                    .commitAllowingStateLoss();
        }
    }
}
