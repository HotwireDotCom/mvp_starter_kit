/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hotwire.com.mvp.sample.home.presenter.ARecentSearchesPresenter;
import hotwire.com.mvp.sample.home.presenter.RecentSearchesFilter;
import hotwire.com.mvp.sample.utilities.Logger;

/**
 * Created by elpark on 2/20/15.
 */
public class AutoCompleteAdapter extends ArrayAdapter<String> implements Filterable, IRecentSearchesAdapter {

    List<String> list;
    ARecentSearchesPresenter recentSearchesPresenter;
    public Context context;

    class ViewHolder {
        public TextView searchText;
    }

    public AutoCompleteAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        list = new ArrayList<String>();
        recentSearchesPresenter = new RecentSearchesFilter(this);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (null == rowView) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.searchText = (TextView) rowView.findViewById(android.R.id.text1);
            rowView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.searchText.setText(list.get(position));

        return rowView;
    }

    @Override
    public int getCount() {
            return list != null ? list.size() : 0;
        }

    @Override
    public String getItem(int index) {
        if (list != null && index < list.size()) {
            return list.get(index);
        } else {
            Logger.errorLog("Error: getItem");
            return null;
        }
    }

    public void resume() {

    }

    @Override
    public Filter getFilter() {
            return recentSearchesPresenter;
        }

    @Override
    public void setValidDataSet(List<String> resultsList) {
        list = resultsList;
        notifyDataSetChanged();
    }

    @Override
    public void notifyInvalidDataSet() {
            notifyDataSetInvalidated();
    }

    public void destroy() {
        recentSearchesPresenter.destroy();
    }


}
