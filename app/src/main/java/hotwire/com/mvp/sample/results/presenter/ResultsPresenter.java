/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.results.presenter;

import java.util.ArrayList;
import java.util.List;

import hotwire.com.mvp.sample.model.dataaccess.DataAccessLayer;
import hotwire.com.mvp.sample.model.dataobjects.search.Search;
import hotwire.com.mvp.sample.model.dataobjects.search.SearchResult;
import hotwire.com.mvp.sample.results.navigator.IResultsNavigator;
import hotwire.com.mvp.sample.results.view.IResultsView;
import hotwire.com.mvp.sample.utilities.Logger;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by elpark on 2/9/15.
 */
public class ResultsPresenter implements IResultsPresenter {

    public static final String TAG = ResultsPresenter.class.getCanonicalName();

    private DataAccessLayer dataAccessLayer;
    private IResultsView view;
    private IResultsNavigator navigator;
    private Subscription searchResultsSubscription;

    public ResultsPresenter(IResultsView view, IResultsNavigator navigator) {

        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void init() {
        dataAccessLayer = DataAccessLayer.getInstance();
        performSearch();
    }

    @Override
    public void destroy() {
        dataAccessLayer = null;
        searchResultsSubscription.unsubscribe();
        searchResultsSubscription = null;
        view = null;
        navigator = null;
    }

    @Override
    public void resultSelected(String title) {
        dataAccessLayer.setResultTitle(title);
        navigator.launchDetailsActivity();
    }

    private void performSearch() {
        String searchTerm = dataAccessLayer.getSearchTerm();
        if (null == searchTerm) {
            navigator.finishActivity();
            return;
        }

        searchResultsSubscription = DataAccessLayer.getInstance().getSearchResultObject(searchTerm).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchResult>() {
            @Override
            public void onCompleted() {
                // NOP
            }

            @Override
            public void onError(Throwable e) {
                searchError();
            }

            @Override
            public void onNext(SearchResult searchResult) {
                processSearchResponse(searchResult);
            }
        });
    }

    private void processSearchResponse(SearchResult searchResult) {
        if (null == searchResult || null == searchResult.getQuery() ||
                null == searchResult.getQuery().getSearch()) {
            searchError();
            return;
        }
        searchResult.setSearchTerm(dataAccessLayer.getSearchTerm());
        searchResult.save();
        Search[] searches = searchResult.getQuery().getSearch();
        List<String> titles = new ArrayList();
        for (int i = 0; i < searches.length; i++) {
            titles.add(searches[i].getTitle());
        }
        view.setResults(titles);
    }

    private void searchError() {
        Logger.errorLog("Error: searchError");
        navigator.finishActivity();
    }
}
