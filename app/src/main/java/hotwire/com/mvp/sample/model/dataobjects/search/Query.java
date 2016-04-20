package hotwire.com.mvp.sample.model.dataobjects.search;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by elpark on 6/23/15.
 */
@Table(name = "Queries")
public class Query extends Model {

    @Column(name = "Searches")
    private Search[] search;

    public Query() {
        super();
    }

    public Search[] getSearch ()
    {
        return search;
    }

    public void setSearch (Search[] search)
    {
        this.search = search;
    }
}