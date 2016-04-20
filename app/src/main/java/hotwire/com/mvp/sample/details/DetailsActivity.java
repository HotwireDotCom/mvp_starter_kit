package hotwire.com.mvp.sample.details;

import android.app.Activity;
import android.os.Bundle;

import hotwire.com.mvp.sample.R;
import hotwire.com.mvp.sample.details.navigator.IDetailsNavigator;
import hotwire.com.mvp.sample.details.view.DetailsFragment;

/**
 * Created by elpark on 6/23/15.
 */
public class DetailsActivity extends Activity implements IDetailsNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        launchDetailsFragment();
    }


    @Override
    public void launchDetailsFragment() {
        DetailsFragment detailsFragment = (DetailsFragment) getFragmentManager().findFragmentByTag(DetailsFragment.TAG);
        if (detailsFragment == null) {
            detailsFragment = new DetailsFragment();
        }
        if (!detailsFragment.isAdded()  && !detailsFragment.isRemoving() && !detailsFragment.isVisible()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment_container, detailsFragment, DetailsFragment.TAG).commit();
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
