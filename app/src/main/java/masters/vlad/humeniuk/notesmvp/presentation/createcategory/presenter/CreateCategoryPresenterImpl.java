package masters.vlad.humeniuk.notesmvp.presentation.createcategory.presenter;

import android.graphics.Color;
import android.text.TextUtils;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.createcategory.view.CreateCategoryView;

public class CreateCategoryPresenterImpl extends RxPresenter implements CreateCategoryPresenter {

    private CreateCategoryView view;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private CategoriesRepository categoriesRepository;

    public CreateCategoryPresenterImpl(Scheduler ioScheduler,
                                       Scheduler uiScheduler,
                                       CategoriesRepository categoriesRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void setView(CreateCategoryView view) {
        this.view = view;
    }

    @Override
    public void saveCategory(String title, String color) {
        if (TextUtils.isEmpty(title) || !isColorValid(color)) {
            view.showEmptyFieldsError();
        } else {
            Category category = new Category();
            category.setName(title);
            category.setColor(color);
            saveCategory(category);
        }
    }

    private void saveCategory(Category category) {
        subscribe(0, categoriesRepository.addCategory(category)
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
