/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home.presenter;

import java.util.ArrayList;
import java.util.List;

import hotwire.com.mvp.sample.home.view.IRecentSearchesAdapter;
import hotwire.com.mvp.sample.model.dataaccess.DataAccessLayer;
import hotwire.com.mvp.sample.model.dataobjects.recentsearch.RecentSearch;
import rx.Observer;
import rx.Subscription;

/**
 * Created by elpark on 2/18/15.
 */
public class RecentSearchesFilter extends ARecentSearchesPresenter {

    private DataAccessLayer dataAccessLayer;
    private final Object lock = new Object();
    private List<String> resultList;
    private List<RecentSearch> recentSearches;
    private IRecentSearchesAdapter recentSearchesView;
    private Subscription recentSearchesSubscription;

    public RecentSearchesFilter(IRecentSearchesAdapter recentSearchesView) {
        this.recentSearchesView = recentSearchesView;
        this.dataAccessLayer = DataAccessLayer.getInstance();
        this.recentSearches = dataAccessLayer.getRecentSearches();
        this.resultList = new ArrayList();

        this.recentSearchesSubscription = dataAccessLayer.subscribeToRecentSearchesPublisher(new Observer<List<RecentSearch>>() {
            @Override
            public void onCompleted() {
                // NOP
            }

            @Override
            public void onError(Throwable e) {
                // NOP
            }

            @Override
            public void onNext(List<RecentSearch> recentSearches) {
                updateRecentSearches(recentSearches);
            }
        });
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint == null) {
            return filterResults;
        }
        /*
         *  If the new pattern starts with the previous pattern,
         *  perform filtering on the current results set.
         */
        String pattern = constraint.toString().trim().toLowerCase();
        resultList.clear();
        for (RecentSearch rs : recentSearches) {
            if (rs.getSearchTerm().toLowerCase().contains(pattern)) {
                    resultList.add(rs.getSearchTerm());
            }
        }

        synchronized (lock) {
            // Assign the data to the FilterResults
            filterResults.values = resultList;
            filterResults.count = resultList.size();
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        synchronized (lock) {

            if (results != null && results.count > 0) {
                recentSearchesView.setValidDataSet((List<String>) results.values);
            } else {
                recentSearchesView.notifyInvalidDataSet();
            }
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {
        if (null != recentSearchesSubscription) {
            recentSearchesSubscription.unsubscribe();
            recentSearchesSubscription = null;
        }
    }

    @Override
    public void updateRecentSearches(List<RecentSearch> recentSearches) {
        this.recentSearches = recentSearches;
    }
}
