package masters.vlad.humeniuk.notesmvp.presentation.noteslist.view;

import java.util.List;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;

public interface NotesListView {

    void showNotesList(List<Note> notes);

    void openNotesDetails(Note note);

    void openCreateNote();

    void openCreateNote(Category category);
}
