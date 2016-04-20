package hotwire.com.mvp.starter.model.dataobjects;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by elpark on 6/19/15.
 */
@Table(name = "Notes")
public class Note extends Model {

    @Column(name = "TimeStamps")
    private long timeStamp;
    @Column(name = "Text")
    private String text;

    public Note() {
        super();
    }

    public Note(long timeStamp, String text) {
        super();
        this.timeStamp = timeStamp;
        this.text = text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
