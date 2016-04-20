/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.details.presenter;

import hotwire.com.mvp.sample.details.navigator.IDetailsNavigator;
import hotwire.com.mvp.sample.model.dataaccess.DataAccessLayer;
import hotwire.com.mvp.sample.details.view.IDetailsView;
import hotwire.com.mvp.sample.model.dataobjects.details.DetailsResult;
import hotwire.com.mvp.sample.utilities.Logger;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by elpark on 2/9/15.
 */
public class DetailsPresenter implements IDetailsPresenter {

    private DataAccessLayer dataAccessLayer;
    private IDetailsView view;
    private IDetailsNavigator navigator;
    private Subscription detailsResultSubscription;

    public DetailsPresenter(IDetailsView view, IDetailsNavigator navigator) {

        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void init() {
        dataAccessLayer = DataAccessLayer.getInstance();
        performSearch();
    }

    private void performSearch() {
        String resultTitle = dataAccessLayer.getResultTitle();
        if (null == resultTitle) {
            Logger.errorLog("Error: resultTitle is null");
            navigator.finishActivity();
            return;
        }
        detailsResultSubscription = dataAccessLayer.getDetailsResultObject(resultTitle).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DetailsResult>() {
            @Override
            public void onCompleted() {
                // NOP
            }

            @Override
            public void onError(Throwable e) {
                Logger.errorLog("Error: details search: " + e.getMessage());
                onDetailsError();
            }

            @Override
            public void onNext(DetailsResult detailsResult) {
                processDetailsResult(detailsResult);
            }
        });
    }

    private void processDetailsResult(DetailsResult detailsResult) {
        if (null == detailsResult) {
            Logger.errorLog("Error: detailsResult is null");
            navigator.finishActivity();
            return;
        }
        detailsResult.save();
        view.setDetailTitle(detailsResult.getTitle());
        view.setDetailText(detailsResult.getExtract());
    }

    @Override
    public void destroy() {
        view = null;
        navigator = null;
        dataAccessLayer = null;
        if (null != detailsResultSubscription) {
            detailsResultSubscription.unsubscribe();
        }
    }


    public void onDetailsError() {
        view.showDetailsSearchErrorMessage("Ahhhhh! I couldn't find any articles with that title.");
    }
}
