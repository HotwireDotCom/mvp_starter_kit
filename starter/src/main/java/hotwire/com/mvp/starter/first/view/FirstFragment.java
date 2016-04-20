package hotwire.com.mvp.starter.first.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import hotwire.com.mvp.R;
import hotwire.com.mvp.starter.first.IFirstNavigator;
import hotwire.com.mvp.starter.first.presenter.FirstPresenter;
import hotwire.com.mvp.starter.first.presenter.IFirstPresenter;

/**
 * Created by elpark on 6/12/15.
 */
public class FirstFragment extends Fragment implements IFirstView {

    public static final String TAG = FirstFragment.class.getCanonicalName();
    private static final String GREETING_TEXT_KEY = "greeting_text_key";

    private IFirstPresenter presenter;
    private TextView greetingTextView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        Button nextScreenButton = (Button) view.findViewById(R.id.next_screen_button);
        nextScreenButton.setOnClickListener((View v) -> presenter.nextScreenButtonClicked());
        this.greetingTextView = (TextView) view.findViewById(R.id.greeting_text);
        presenter = new FirstPresenter(this, (IFirstNavigator) getActivity());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == savedInstanceState || !savedInstanceState.containsKey(GREETING_TEXT_KEY)) {
            presenter.viewCreated();
        } else if (savedInstanceState.containsKey(GREETING_TEXT_KEY)) {
            greetingTextView.setText(savedInstanceState.getString(GREETING_TEXT_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String greetingText = greetingTextView.getText().toString();
        if (!greetingText.isEmpty()) {
            outState.putString(GREETING_TEXT_KEY, greetingTextView.getText().toString());
        }
    }

    @Override
    public void setGreetingText(String greetingText) {
        this.greetingTextView.setText(greetingText);
    }

    @Override
    public void onError(String message) {
        Log.e(TAG, "Error: " + message);
    }
}
