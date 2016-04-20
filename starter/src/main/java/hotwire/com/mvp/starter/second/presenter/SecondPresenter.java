package hotwire.com.mvp.starter.second.presenter;

import java.util.ArrayList;
import java.util.List;

import hotwire.com.mvp.starter.model.dataaccess.DataAccessLayer;
import hotwire.com.mvp.starter.model.dataobjects.Note;
import hotwire.com.mvp.starter.second.ISecondNavigator;
import hotwire.com.mvp.starter.second.view.ISecondView;

/**
 * Created by elpark on 6/22/15.
 */
public class SecondPresenter implements ISecondPresenter {

    public static final String TAG = SecondPresenter.class.getCanonicalName();

    private ISecondView view;
    private ISecondNavigator navigator;

    public SecondPresenter(ISecondView view, ISecondNavigator navigator) {
        this.view = view;
    }

    @Override
    public void viewCreated() {
        List<Note> notes = DataAccessLayer.getInstance().getNotes("", null);
        setNoteList(notes);
    }

    @Override
    public void saveNote(String noteString) {
        Note note = new Note(System.currentTimeMillis(), noteString);
        note.save();
        List<Note> notes = DataAccessLayer.getInstance().getNotes("", null);
        setNoteList(notes);
    }

    private void setNoteList(List<Note> notes) {
        List<String> noteStrings = new ArrayList<>(notes.size());
        for (Note n : notes) {
            noteStrings.add(n.getText());
        }
        view.setUpList(noteStrings);
    }
}
