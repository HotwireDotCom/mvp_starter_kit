package hotwire.com.mvp.starter.model.dataaccess;

import com.activeandroid.query.Select;

import java.util.List;

import hotwire.com.mvp.starter.model.dataobjects.Greeting;
import hotwire.com.mvp.starter.model.dataobjects.Note;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by elpark on 6/16/15.
 */
public class DataAccessLayer {

    public static String TAG = DataAccessLayer.class.getCanonicalName();

    private static DataAccessLayer instance;
    private RestService restService;

    public interface RestService {
        @GET("/greeting/{type}")
        Observable<Greeting> getGreeting(@Path("type") String type);
    }

    private DataAccessLayer() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("mock_endpoint").setClient(new MockClient()).build();
        restService = restAdapter.create(RestService.class);
    }

    public static DataAccessLayer getInstance() {
        if (null == instance) {
            instance = new DataAccessLayer();
        }
        return instance;
    }

    public Observable<Greeting> getGreeting(String type) {
        return restService.getGreeting(type);
    }

    public List<Note> getNotes(String clause, Object... args) {
        if (null != clause && !clause.isEmpty()) {
            return new Select().from(Note.class).where(clause, args).execute();
        } else {
            return new Select().from(Note.class).execute();
        }
    }
}
