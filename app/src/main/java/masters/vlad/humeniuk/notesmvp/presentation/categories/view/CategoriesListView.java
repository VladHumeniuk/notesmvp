package masters.vlad.humeniuk.notesmvp.presentation.categories.view;

import java.util.List;

import masters.vlad.humeniuk.notesmvp.domain.entity.Category;

public interface CategoriesListView {

    void showCategories(List<Category> categories);

    void showCategoryNotes(Category category);

    void showAddCategory();

    void showEditCategory(Category category);
}
