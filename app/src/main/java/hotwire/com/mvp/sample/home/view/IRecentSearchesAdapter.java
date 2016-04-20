/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.home.view;

import java.util.List;

/**
 * Created by elpark on 2/18/15.
 */
public interface IRecentSearchesAdapter {

    void setValidDataSet(List<String> resultsList);
    void notifyInvalidDataSet();
}
