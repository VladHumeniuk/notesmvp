package masters.vlad.humeniuk.notesmvp.presentation.editnote.view;

import java.util.List;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;

public interface EditNoteView {

    void showNote(Note note);

    void showEmptyFieldsError();

    void showCategories(List<Category> categoryList, Category selectedCategory);

    void backToMain();
}
