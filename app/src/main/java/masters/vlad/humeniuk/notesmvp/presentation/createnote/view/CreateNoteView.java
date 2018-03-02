package masters.vlad.humeniuk.notesmvp.presentation.createnote.view;

import java.util.List;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;

public interface CreateNoteView {

    void showEmptyFieldsError();

    void showDefaultCategory(Category category);

    void showCategories(List<Category> categoryList);

    void backToMain();
}
