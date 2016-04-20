package hotwire.com.mvp.starter.first.presenter;

import android.util.Log;

import hotwire.com.mvp.starter.first.IFirstNavigator;
import hotwire.com.mvp.starter.first.view.IFirstView;
import hotwire.com.mvp.starter.model.dataaccess.DataAccessLayer;
import hotwire.com.mvp.starter.model.dataobjects.Greeting;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by elpark on 6/12/15.
 */
public class FirstPresenter implements IFirstPresenter {

    public static String TAG = FirstPresenter.class.getCanonicalName();

    private IFirstView view;
    private IFirstNavigator navigator;

    public FirstPresenter(IFirstView view, IFirstNavigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void viewCreated() {
        DataAccessLayer.getInstance().getGreeting(Greeting.FRIENDLY).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Greeting>() {
                    @Override
                    public void onCompleted() {
                        // NOP
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Error: Could not fetch greeting: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Greeting greeting) {
                        view.setGreetingText(greeting.getContent());
                    }
                });
    }

    @Override
    public void nextScreenButtonClicked() {
        navigator.launchNextScreen();
    }
}
