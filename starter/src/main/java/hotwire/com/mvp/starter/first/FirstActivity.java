package hotwire.com.mvp.starter.first;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import hotwire.com.mvp.R;
import hotwire.com.mvp.starter.first.view.FirstFragment;
import hotwire.com.mvp.starter.second.SecondActivity;


public class FirstActivity extends ActionBarActivity implements IFirstNavigator {

    public static String TAG = FirstActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        launchFirstFragment();
    }

    @Override
    public void launchFirstFragment() {
        FirstFragment fragment = (FirstFragment) getSupportFragmentManager()
                .findFragmentByTag(FirstFragment.TAG);
        if (null == fragment) {
            fragment = new FirstFragment();
        }
        if (!fragment.isAdded()  && !fragment.isRemoving() && !fragment.isVisible()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, FirstFragment.TAG)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void launchNextScreen() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.starter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
