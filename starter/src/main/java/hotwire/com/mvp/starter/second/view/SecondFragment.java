package hotwire.com.mvp.starter.second.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hotwire.com.mvp.R;
import hotwire.com.mvp.starter.second.ISecondNavigator;
import hotwire.com.mvp.starter.second.presenter.ISecondPresenter;
import hotwire.com.mvp.starter.second.presenter.SecondPresenter;

/**
 * Created by elpark on 6/12/15.
 */
public class SecondFragment extends Fragment implements ISecondView {

    public static String TAG = SecondFragment.class.getCanonicalName();

    private ISecondPresenter presenter;
    private ArrayAdapter adapter;
    private List<String> noteList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        presenter = new SecondPresenter(this, (ISecondNavigator) activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.notes_list_view);
        noteList = new ArrayList();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, noteList);
        listView.setAdapter(adapter);
        EditText noteInputEditText = (EditText) view.findViewById(R.id.notes_edit_text);
        Button saveNoteButton = (Button) view.findViewById(R.id.save_note_button);

        saveNoteButton.setOnClickListener((View v) -> {
            String text;
            if (!(text = noteInputEditText.getText().toString()).isEmpty()) {
                presenter.saveNote(text);
                noteInputEditText.setText("");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.viewCreated();
    }

    @Override
    public void setUpList(List<String> list) {
        noteList.clear();
        noteList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
