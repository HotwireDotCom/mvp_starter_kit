/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import hotwire.com.mvp.sample.model.dataobjects.details.DetailsResult;

/**
 * Created by elpark on 2/9/15.
 */
public class JSONProcessor {

    private static String QUERY_KEY = "query";
    private static String TITLE_KEY = "title";
    private static String PAGES = "pages";
    private static String EXTRACT = "extract";

    public static DetailsResult createDetailsResult(String detailsJSON) throws JSONException {

        JSONObject root = new JSONObject(detailsJSON);
        JSONObject query = root.getJSONObject(QUERY_KEY);
        JSONObject pages = query.getJSONObject(PAGES);
        String key = pages.keys().next();
        JSONObject page = pages.getJSONObject(key);

        String title = page.getString(TITLE_KEY);
        String extract = page.getString(EXTRACT);

        if (null != extract) {
            extract = removeXML(extract);
        }

        DetailsResult detailsResult = new DetailsResult(System.currentTimeMillis(), title, extract);

        return detailsResult;
    }

    /**
     *
     * @param text
     *      the text to remove XML from.
     * @return the text without any xml.
     */
    public static String removeXML(String text) {

        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = text.toCharArray();
        boolean isXML = false;
        for (char c : chars) {

            switch (c) {
                case '<':
                    isXML = true;
                    break;
                case '>':
                    isXML = false;
                    break;
                default:
                    if (!isXML) {
                        stringBuilder.append(c);
                    }
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
