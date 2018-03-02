package masters.vlad.humeniuk.notesmvp.presentation.editnote.presenter;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.view.EditNoteView;

public interface EditNotePresenter {

    void setNote(Note note);

    void saveNote(String title, String description, Category category);

    void setView(EditNoteView view);

    void deleteNote();
}
