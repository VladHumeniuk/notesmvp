package masters.vlad.humeniuk.notesmvp.presentation.createnote.presenter;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.view.CreateNoteView;

public interface CreateNotePresenter {

    void onSaveNote(String title, String description, Category category);

    void setView(CreateNoteView view);

    void setDefaultCategory(Category category);
}
