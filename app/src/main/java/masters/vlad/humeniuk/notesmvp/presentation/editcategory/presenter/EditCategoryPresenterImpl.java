package masters.vlad.humeniuk.notesmvp.presentation.editcategory.presenter;

import android.graphics.Color;
import android.text.TextUtils;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.editcategory.view.EditCategoryView;

public class EditCategoryPresenterImpl extends RxPresenter implements EditCategoryPresenter {

    private EditCategoryView view;

    private Category category;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private CategoriesRepository categoriesRepository;

    public EditCategoryPresenterImpl(Scheduler ioScheduler,
                                     Scheduler uiScheduler,
                                     CategoriesRepository categoriesRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
        view.showCategory(category);
    }

    @Override
    public void onSave(String title, String color) {
        if (TextUtils.isEmpty(title) || !isColorValid(color)) {
            view.showEmptyFieldsError();
        } else {
            category.setName(title);
            category.setColor(color);
            saveCategory(category);
        }
    }

    @Override
    public void onDelete() {
        subscribe(1, categoriesRepository.deleteCategory(category)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> view.backToMain()));
    }

    @Override
    public void setView(EditCategoryView view) {
        this.view = view;
    }

    private void saveCategory(Category category) {
        subscribe(0, categoriesRepository.editCategory(category)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> view.backToMain()));
    }

    private boolean isColorValid(String color) {
        try {
            Color.parseColor(color);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
