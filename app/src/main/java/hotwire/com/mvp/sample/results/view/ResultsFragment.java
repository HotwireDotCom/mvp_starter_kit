/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.results.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import hotwire.com.mvp.sample.R;
import hotwire.com.mvp.sample.results.navigator.IResultsNavigator;
import hotwire.com.mvp.sample.results.presenter.IResultsPresenter;
import hotwire.com.mvp.sample.results.presenter.ResultsPresenter;

/**
 * Created by elpark on 2/9/15.
 */
public class ResultsFragment extends Fragment implements IResultsView {

    public static final String TAG = IResultsView.class.getCanonicalName();

    private IResultsPresenter resultsPresenter;
    private ListView resultsListView;
    private ResultsArrayAdapter resultsAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resultsPresenter = new ResultsPresenter(this, (IResultsNavigator) activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.results_fragment, container, false);
        resultsListView = (ListView) v.findViewById(R.id.results_list_view);
        resultsListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            resultsPresenter.resultSelected(resultsAdapter.getTitle(position));
        });
        resultsPresenter.init();
        return v;
    }

    @Override
    public void setResults(List<String> resultsList) {

        Log.d("debug", "ResultsView: setting resultsList");
        if (null == resultsAdapter) {
            resultsAdapter = new ResultsArrayAdapter(getActivity(), resultsList);
            resultsListView.setAdapter(resultsAdapter);
        } else {
            resultsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        resultsPresenter.destroy();
        super.onDestroy();
    }


    private class ResultsArrayAdapter extends ArrayAdapter<String> {

        private Context mContext;
        private List<String> mResultList;

        class ViewHolder {
            public TextView resultText;
        }

        public ResultsArrayAdapter(Context context, List<String> resultList) {
            super(context, R.layout.results_list_item, resultList);
            mContext = context;
            mResultList = resultList;
        }

        public String getTitle(int position) {
            if (position < mResultList.size()) {
                return mResultList.get(position);
            } else {
                return null;
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (null == rowView) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.results_list_item, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.resultText = (TextView) rowView.findViewById(R.id.result_text);
                rowView.setTag(viewHolder);
            }

            ViewHolder viewHolder = (ViewHolder) rowView.getTag();
            viewHolder.resultText.setText(mResultList.get(position));

            return rowView;
        }
    }
}












