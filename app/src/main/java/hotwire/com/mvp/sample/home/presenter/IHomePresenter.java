/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home.presenter;

/**
 * Created by elpark on 2/9/15.
 */
public interface IHomePresenter {

    void init();
    void destroy();
    void search(String text);
}
