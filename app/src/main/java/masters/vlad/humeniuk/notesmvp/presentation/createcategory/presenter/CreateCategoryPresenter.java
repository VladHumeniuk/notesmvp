package masters.vlad.humeniuk.notesmvp.presentation.createcategory.presenter;

import masters.vlad.humeniuk.notesmvp.presentation.createcategory.view.CreateCategoryView;

public interface CreateCategoryPresenter {

    void setView(CreateCategoryView view);

    void saveCategory(String title, String color);
}
