package masters.vlad.humeniuk.notesmvp.presentation.editcategory.view;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;

public interface EditCategoryView {

    void showCategory(Category category);

    void showEmptyFieldsError();

    void backToMain();
}
