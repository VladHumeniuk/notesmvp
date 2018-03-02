package masters.vlad.humeniuk.notesmvp.presentation.categories.presenter;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.presentation.categories.view.CategoriesListView;

public interface CategoriesListPresenter {

    void onCategorySelected(Category category);

    void onCategoryLongClick(Category category);

    void onAddCategoryClick();

    void init();

    void setView(CategoriesListView view);
}
