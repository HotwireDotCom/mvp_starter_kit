/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.results.presenter;

/**
 * Created by elpark on 2/9/15.
 */
public interface IResultsPresenter {

    /*
     * No getter for the results list. The View is passive and will be updated by the presenter.
     */
    void init();
    void destroy();
    void resultSelected(String resultTitle);
}
