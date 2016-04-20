/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.results;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import hotwire.com.mvp.sample.R;
import hotwire.com.mvp.sample.details.DetailsActivity;
import hotwire.com.mvp.sample.results.navigator.IResultsNavigator;
import hotwire.com.mvp.sample.results.view.ResultsFragment;

/**
 * Created by elpark on 2/9/15.
 */
public class ResultsActivity extends ActionBarActivity implements IResultsNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);
        launchResultsFragment();
    }

    @Override
    public void launchResultsFragment() {
        ResultsFragment resultsFragment = (ResultsFragment) getFragmentManager().findFragmentByTag(ResultsFragment.TAG);
        if (resultsFragment == null) {
            resultsFragment = new ResultsFragment();
        }
        if (!resultsFragment.isAdded()  && !resultsFragment.isRemoving() && !resultsFragment.isVisible()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.results_fragment_container, resultsFragment, ResultsFragment.TAG).commit();
        }
    }

    @Override
    public void launchDetailsActivity() {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
