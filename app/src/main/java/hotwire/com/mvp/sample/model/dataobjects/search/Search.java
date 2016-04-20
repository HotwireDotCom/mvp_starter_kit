package hotwire.com.mvp.sample.model.dataobjects.search;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by elpark on 6/23/15.
 */
@Table(name = "Searches")
public class Search extends Model {

    @Column(name = "TimeStamps")
    private String timestamp;
    @Column(name = "Titles")
    private String title;
    @Column(name = "Ns")
    private String ns;
    @Column(name = "Snippets")
    private String snippet;
    @Column(name = "WordCounts")
    private String wordcount;
    @Column(name = "Sizes")
    private String size;

    public Search() {
        super();
    }

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getNs ()
    {
        return ns;
    }

    public void setNs (String ns)
    {
        this.ns = ns;
    }

    public String getSnippet ()
    {
        return snippet;
    }

    public void setSnippet (String snippet)
    {
        this.snippet = snippet;
    }

    public String getWordcount ()
    {
        return wordcount;
    }

    public void setWordcount (String wordcount)
    {
        this.wordcount = wordcount;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }
}
