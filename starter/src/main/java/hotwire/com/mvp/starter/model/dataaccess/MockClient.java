package hotwire.com.mvp.starter.model.dataaccess;

import android.net.Uri;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import hotwire.com.mvp.starter.model.dataobjects.Greeting;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by elpark on 6/16/15.
 */
public class MockClient implements Client {

    private String[] friendlyGreetingResponses = {"Hello!", "Hi", "What's up?", "Good morning"};
    private String[] rudeGreetingResponses = {"Go away.", "What do you want?", "Again? Really?."};

    @Override
    public Response execute(Request request) throws IOException {
        Uri uri = Uri.parse(request.getUrl());
        String responseString = null;
        if(uri.getPath().matches("mock_endpoint/greeting/" + Greeting.FRIENDLY)) {
            Random random = new Random();
            responseString = "{\"content\":\"" + friendlyGreetingResponses[random.nextInt(friendlyGreetingResponses.length)] + "\",\"timeStamp\":" + System.currentTimeMillis() + "}";
        } else if (uri.getPath().matches("mock_endpoint/greeting/" + Greeting.RUDE)) {
            Random random = new Random();
            responseString = "{\"content\":\"" + rudeGreetingResponses[random.nextInt(rudeGreetingResponses.length)] + "\",\"timeStamp\":" + System.currentTimeMillis() + "}";
        } else {
            responseString = "{\"content\":\"Invalid Request\",\"timeStamp\":" + System.currentTimeMillis() + "}";
        }
        return new Response(request.getUrl(), 200, "none", Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));
    }
}
