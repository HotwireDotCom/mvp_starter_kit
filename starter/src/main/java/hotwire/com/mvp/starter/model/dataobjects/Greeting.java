package hotwire.com.mvp.starter.model.dataobjects;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by elpark on 6/16/15.
 */
@Table(name = "Greetings")
public class Greeting extends Model {

    public static final String FRIENDLY = "friendly";
    public static final String RUDE = "rude";

    @Column(name = "TimeStamps")
    private long timeStamp;
    @Column(name = "Contents")
    private String content;

    public Greeting() {
        super();
    }

    public Greeting(long timeStamp, String content) {
        super();
        this.timeStamp = timeStamp;
        this.content = content;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
