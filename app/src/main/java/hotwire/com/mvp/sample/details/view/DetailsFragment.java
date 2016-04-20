/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.details.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hotwire.com.mvp.sample.R;
import hotwire.com.mvp.sample.details.navigator.IDetailsNavigator;
import hotwire.com.mvp.sample.details.presenter.DetailsPresenter;
import hotwire.com.mvp.sample.details.presenter.IDetailsPresenter;

/**
 * Created by elpark on 2/9/15.
 */
public class DetailsFragment extends Fragment implements IDetailsView {

    public static final String TAG = IDetailsView.class.getCanonicalName();

    private IDetailsPresenter mPresenter;
    private TextView mTitleTextView;
    private TextView mDetailsTextView;

    /*
     * Called when a fragment is first attached to its activity. onCreate(Bundle) will be called after this.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mPresenter = new DetailsPresenter(this, (IDetailsNavigator) activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.details_fragment, container, false);
        mTitleTextView = (TextView) v.findViewById(R.id.detail_title_text);
        mDetailsTextView = (TextView) v.findViewById(R.id.detail_text);
        mPresenter.init();
        return v;
    }

    @Override
    public void setDetailTitle(String titleText) {
        mTitleTextView.setText(titleText);
    }

    @Override
    public void setDetailText(String detailText) {
        mDetailsTextView.setText(detailText);
    }

    @Override
    public void showDetailsSearchErrorMessage(String message) {
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

}
