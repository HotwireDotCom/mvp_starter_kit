/*
 * Copyright 2015 Hotwire. All Rights Reserved.
 *
 * This software is the proprietary information of Hotwire.
 * Use is subject to license terms.
 */
package hotwire.com.mvp.sample.model.dataaccess;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import hotwire.com.mvp.sample.model.dataobjects.details.DetailsResult;
import hotwire.com.mvp.sample.model.dataobjects.recentsearch.RecentSearch;
import hotwire.com.mvp.sample.model.dataobjects.search.SearchResult;
import hotwire.com.mvp.sample.utilities.JSONProcessor;
import retrofit.RestAdapter;
import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.mime.TypedInput;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by elpark on 2/9/15.
 */
public class DataAccessLayer {

    public static final String EN_WIKIPEDIA_ENDPOINT = "http://en.wikipedia.org";

    private static DataAccessLayer instance;

    private RestService restService;

    private String searchTerm;
    private String resultTitle;

    private PublishSubject<List<RecentSearch>> recentSearchesPublisher;


    public interface RestService {

        @GET("/w/api.php?action=query&continue=&list=search&format=json")
        Observable<SearchResult> getSearchResult(@Query("srsearch") String srsearch);

        @GET("/w/api.php?action=query&continue=&prop=extracts&format=json&exintro")
        Observable<DetailsResult> getDetailsResult(@Query("titles") String titles);
    }

    protected DataAccessLayer() {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(EN_WIKIPEDIA_ENDPOINT).setConverter(new CustomGsonConverter(new Gson())).build();
        restService = restAdapter.create(RestService.class);
        recentSearchesPublisher = PublishSubject.create();
    }

    public Observable<SearchResult> getSearchResultObject(String searchTerm) {
        if (0 == new Select().from(RecentSearch.class)
                .where("SearchTerm = ?", searchTerm).count()) {
            new RecentSearch(System.currentTimeMillis(), searchTerm).save();
        }
        return restService.getSearchResult(searchTerm);
    }

    public Observable<DetailsResult> getDetailsResultObject(String title) {

        return restService.getDetailsResult(title);
    }

    public static DataAccessLayer getInstance() {

        if (null == instance) {
            instance = new DataAccessLayer();
        }
        return instance;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getResultTitle() {
        return resultTitle;
    }

    public void setResultTitle(String resultTitle) {
        this.resultTitle = resultTitle;
    }

    public List<RecentSearch> getRecentSearches() {
        if (0 != new Select().from(RecentSearch.class).count()) {
            return new Select().from(RecentSearch.class).execute();
        } else {
            return new ArrayList<RecentSearch>();
        }
    }

    public Subscription subscribeToRecentSearchesPublisher(Observer<List<RecentSearch>> observer) {
        return recentSearchesPublisher.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void clearRecentSearches() {
        new Delete().from(RecentSearch.class).execute();
        recentSearchesPublisher.onNext(new ArrayList<RecentSearch>());
    }

    public class CustomGsonConverter extends GsonConverter {

        public CustomGsonConverter(Gson gson) {
            super(gson);
        }

        @Override
        public Object fromBody(TypedInput body, Type type) throws ConversionException {
            // This type comparison is a bit hacky, but you get the point.
            if (type.toString().endsWith(DetailsResult.class.getName())) {
                DetailsResult detailsResult = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader bufferedReader = null;
                try {
                    inputStreamReader = new InputStreamReader(body.in());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String newLine = System.getProperty("line.separator");
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append(newLine);
                    }
                    detailsResult = JSONProcessor.createDetailsResult(stringBuilder.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != bufferedReader) {
                            bufferedReader.close();
                        }
                        if (null != inputStreamReader) {
                            inputStreamReader.close();
                        }
                        if (null != body && null != body.in()) {
                            body.in().close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return detailsResult;
            } else {
                return super.fromBody(body, type);
            }
        }
    }
}
