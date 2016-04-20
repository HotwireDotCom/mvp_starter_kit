package hotwire.com.mvp.sample.model.dataobjects.recentsearch;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by elpark on 6/23/15.
 */
@Table(name = "RecentSearches")
public class RecentSearch extends Model {

    @Column(name = "TimeStamps")
    private long timeStamp;
    @Column(name = "SearchTerm")
    private String searchTerm;

    public RecentSearch() {
        super();
    }

    public RecentSearch(long timeStamp, String searchTerm) {
        super();
        this.timeStamp = timeStamp;
        this.searchTerm = searchTerm;
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
