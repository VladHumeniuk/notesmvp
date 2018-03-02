package masters.vlad.humeniuk.notesmvp.presentation.main.presenter;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.base.RxPresenter;

public class MainPresenterImpl extends RxPresenter implements MainPresenter {

    private Scheduler ioScheduler;

    private CategoriesRepository categoriesRepository;

    public MainPresenterImpl(Scheduler ioScheduler,
                             CategoriesRepository categoriesRepository) {
        this.ioScheduler = ioScheduler;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void init() {
        subscribe(0, categoriesRepository.init()
                .subscribeOn(ioScheduler)
                .subscribe());
    }
}
