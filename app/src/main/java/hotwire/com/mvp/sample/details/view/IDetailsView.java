/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.details.view;

/**
 * Created by elpark on 2/9/15.
 */
public interface IDetailsView {

    void showDetailsSearchErrorMessage(String message);
    void setDetailTitle(String titleText);
    void setDetailText(String detailText);
}
