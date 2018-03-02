package masters.vlad.humeniuk.notesmvp.domain.repositories;

import java.util.List;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;

public interface CategoriesRepository {

    Observable<Category> addCategory(Category category);

    Observable<List<Category>> getCategoriesList();

    Observable<Boolean> deleteCategory(Category category);

    Observable<Category> editCategory(Category category);

    Observable<Long> init();
}
