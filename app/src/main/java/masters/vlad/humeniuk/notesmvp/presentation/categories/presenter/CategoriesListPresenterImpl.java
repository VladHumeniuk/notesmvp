package masters.vlad.humeniuk.notesmvp.presentation.categories.presenter;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.categories.view.CategoriesListView;

public class CategoriesListPresenterImpl extends RxPresenter implements CategoriesListPresenter {

    private static final int LOAD_CATEGORIES = 1;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CategoriesListView view;

    private CategoriesRepository categoriesRepository;

    public CategoriesListPresenterImpl(Scheduler ioScheduler,
                                       Scheduler uiScheduler,
                                       CategoriesRepository categoriesRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void onCategorySelected(Category category) {
        view.showCategoryNotes(category);
    }

    @Override
    public void onCategoryLongClick(Category category) {
        if (category.getId() != 1) {
            view.showEditCategory(category);
        }
    }

    @Override
    public void onAddCategoryClick() {
        view.showAddCategory();
    }

    @Override
    public void init() {
        subscribe(LOAD_CATEGORIES, categoriesRepository.getCategoriesList()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(view::showCategories));
    }

    @Override
    public void setView(CategoriesListView view) {
        this.view = view;
    }
}
