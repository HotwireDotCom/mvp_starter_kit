/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home.presenter;

import hotwire.com.mvp.sample.home.navigator.IHomeNavigator;
import hotwire.com.mvp.sample.model.dataaccess.DataAccessLayer;
import hotwire.com.mvp.sample.home.view.IHomeView;
import hotwire.com.mvp.sample.utilities.Logger;

/**
 * Created by elpark on 2/9/15.
 */
public class HomePresenter implements IHomePresenter {

    private DataAccessLayer dataAccessLayer;
    private IHomeView view;
    private IHomeNavigator navigator;

    public HomePresenter(IHomeView homeView, IHomeNavigator homeNavigator) {
        view = homeView;
        navigator = homeNavigator;
    }

    @Override
    public void init() {

        dataAccessLayer = DataAccessLayer.getInstance();
    }

    @Override
    public void search(String searchTerm) {

        if (null == searchTerm) {
            Logger.errorLog("Error: search term is null");
            return;
        }
        searchTerm = searchTerm.trim();
        dataAccessLayer.setSearchTerm(searchTerm);
        view.setSearchTerm("");
        navigator.launchResultsPage();
    }

    @Override
    public void destroy() {
        dataAccessLayer = null;
        view = null;
        navigator = null;
    }
}
