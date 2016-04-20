package hotwire.com.mvp.sample.model.dataobjects.details;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by elpark on 6/23/15.
 */
@Table(name = "DetailsResults")
public class DetailsResult extends Model {

    @Column(name = "TimeStamps")
    private long timeStamp;
    @Column(name = "Titles")
    private String title;
    @Column(name = "Extracts")
    private String extract;

    public DetailsResult() {
        super();
    }

    public DetailsResult(long timeStamp, String title, String extract) {
        super();
        this.timeStamp = timeStamp;
        this.title = title;
        this.extract = extract;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
