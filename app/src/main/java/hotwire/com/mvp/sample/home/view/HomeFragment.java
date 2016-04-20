/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;


import hotwire.com.mvp.sample.R;
import hotwire.com.mvp.sample.home.navigator.IHomeNavigator;
import hotwire.com.mvp.sample.home.presenter.HomePresenter;
import hotwire.com.mvp.sample.home.presenter.IHomePresenter;
import hotwire.com.mvp.sample.utilities.Logger;

/**
 * Created by elpark on 2/9/15.
 */
public class HomeFragment extends Fragment implements IHomeView {


    public static final String TAG = HomeFragment.class.getCanonicalName();

    private IHomePresenter presenter;
    private AutoCompleteTextView searchEditText;
    private ImageView searchButton;
    private AutoCompleteAdapter autoCompleteAdapter;

    /*
     * Called when a fragment is first attached to its activity. onCreate(Bundle) will be called after this.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        presenter = new HomePresenter(this, (IHomeNavigator) activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment, container, false);
        initWidgets(v);
        presenter.init();
        return v;
    }

    private void initWidgets(View view) {

        searchEditText = (AutoCompleteTextView) view.findViewById(R.id.home_search_field);
        autoCompleteAdapter = new AutoCompleteAdapter(getActivity(),
                android.R.layout.simple_dropdown_item_1line);
        searchEditText.setAdapter(autoCompleteAdapter);
        searchEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchEditText.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.search(searchEditText.getText().toString());
                return true;
            }
            return false;
        });
        searchButton = (ImageView) view.findViewById(R.id.home_search_button);
        searchButton.setOnClickListener((View v) -> presenter.search(searchEditText.getText().toString()));
    }

    @Override
    public void setSearchTerm(String searchTerm) {
        if (null != searchEditText) {
            searchEditText.setText(searchTerm);
        } else {
            Logger.errorLog("setSearchTerm: searchEditText is null: " + searchTerm);
        }
    }

    public IHomePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        autoCompleteAdapter.resume();
    }

    @Override
    public void onDestroy() {
        autoCompleteAdapter.destroy();
        autoCompleteAdapter = null;
        presenter.destroy();
        presenter = null;
        super.onDestroy();
    }

    /*
     * Called when the fragment is no longer attached to its activity. This is called after onDestroy().
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

}
