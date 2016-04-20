package hotwire.com.mvp.sample.model.dataobjects.search;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by elpark on 6/23/15.
 */
@Table(name = "SearchResults")
public class SearchResult extends Model {

    @Column(name = "SearchTerms")
    private String searchTerm;
    @Column(name = "TimeStamps")
    private long timeStamp;
    @Column(name = "Queries")
    private Query query;

    public SearchResult() {
        super();
        timeStamp = System.currentTimeMillis();
        searchTerm = new String();
    }

    public Query getQuery ()
    {
        return query;
    }

    public void setQuery (Query query)
    {
        this.query = query;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
