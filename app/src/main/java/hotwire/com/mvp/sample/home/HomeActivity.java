/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import hotwire.com.mvp.sample.R;
import hotwire.com.mvp.sample.home.navigator.IHomeNavigator;
import hotwire.com.mvp.sample.home.view.HomeFragment;
import hotwire.com.mvp.sample.model.dataaccess.DataAccessLayer;
import hotwire.com.mvp.sample.results.ResultsActivity;


public class HomeActivity extends ActionBarActivity implements IHomeNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        launchHomePage();
    }

    @Override
    public void launchHomePage() {
        HomeFragment homeFragment = (HomeFragment) getFragmentManager().findFragmentByTag(HomeFragment.TAG);
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        if (!homeFragment.isAdded()  && !homeFragment.isRemoving() && !homeFragment.isVisible()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment_container, homeFragment, HomeFragment.TAG).commit();
        }
    }

    @Override
    public void launchResultsPage() {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_search_history) {

            HomeFragment homeFragment = (HomeFragment) getFragmentManager().findFragmentByTag(HomeFragment.TAG);
            if (null != homeFragment) {
                DataAccessLayer.getInstance().clearRecentSearches();
                return true;
            } else {
                return false;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
