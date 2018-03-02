package masters.vlad.humeniuk.notesmvp.presentation.noteslist.presenter;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.view.NotesListView;

public interface NotesListPresenter {

    void setCategory(Category category);

    void loadNotes();

    void onNoteSelected(Note note);

    void onCreateNote();

    void setView(NotesListView view);
}
