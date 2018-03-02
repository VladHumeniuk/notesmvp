package masters.vlad.humeniuk.notesmvp.presentation.noteslist.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseFragment;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.ItemSelectedListener;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.CreateNoteActivity;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.EditNoteActivity;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.presenter.NotesListPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.view.list.NotesAdapter;

public class NotesListFragment extends BaseFragment implements NotesListView {

    public static final int REQUEST_CREATE_NOTE = 11;
    public static final int REQUEST_EDIT_NOTE = 12;

    private static final String ARG_CATEGORY = "category";

    @BindView(R.id.notes_list)
    protected RecyclerView notesListView;

    @BindView(R.id.empty_view)
    protected View emptyView;

    @Inject
    protected NotesListPresenter presenter;

    private NotesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static NotesListFragment newInstance() {

        Bundle args = new Bundle();

        NotesListFragment fragment = new NotesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static NotesListFragment newInstance(Category category) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_CATEGORY, category);

        NotesListFragment fragment = new NotesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notes_list;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        adapter = new NotesAdapter(getContext(), listener);
        layoutManager = new LinearLayoutManager(getContext());
        notesListView.setLayoutManager(layoutManager);
        notesListView.setAdapter(adapter);
        presenter.setCategory((Category) getArguments().get(ARG_CATEGORY));
        presenter.loadNotes();
        return view;
    }

    @Override
    public void showNotesList(List<Note> notes) {
        adapter.setData(notes);
        boolean empty = notes.isEmpty();
        emptyView.setVisibility(empty ? View.VISIBLE : View.GONE);
        notesListView.setVisibility(empty ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.add_note)
    protected void onAddNoteClick() {
        presenter.onCreateNote();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            presenter.loadNotes();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void openNotesDetails(Note note) {
        startActivityForResult(EditNoteActivity.newIntent(getActivity(), note),
                REQUEST_EDIT_NOTE);
    }

    @Override
    public void openCreateNote() {
        openCreateNote(null);
    }

    @Override
    public void openCreateNote(Category category) {
        startActivityForResult(CreateNoteActivity.newIntent(getActivity(), category),
                REQUEST_CREATE_NOTE);
    }

    private ItemSelectedListener<Note> listener = new ItemSelectedListener<Note>() {

        @Override
        public void onItemSelected(Note entity) {
            presenter.onNoteSelected(entity);
        }
    };
}
