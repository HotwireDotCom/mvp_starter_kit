/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home.presenter;

import android.widget.Filter;

import java.util.List;

import hotwire.com.mvp.sample.model.dataobjects.recentsearch.RecentSearch;

/**
 * Created by elpark on 2/19/15.
 */
public abstract class ARecentSearchesPresenter extends Filter {

    public abstract void resume();
    public abstract void destroy();
    public abstract void updateRecentSearches(List<RecentSearch> recentSearches);
}
