package masters.vlad.humeniuk.notesmvp.presentation.editcategory.presenter;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.presentation.editcategory.view.EditCategoryView;

public interface EditCategoryPresenter {

    void setCategory(Category category);

    void onSave(String title, String color);

    void onDelete();

    void setView(EditCategoryView view);
}
