package masters.vlad.humeniuk.notesmvp.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.di.scopes.UserScope;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.domain.repositories.NotesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.categories.presenter.CategoriesListPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.categories.presenter.CategoriesListPresenterImpl;
import masters.vlad.humeniuk.notesmvp.presentation.createcategory.presenter.CreateCategoryPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.createcategory.presenter.CreateCategoryPresenterImpl;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.presenter.CreateNotePresenter;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.presenter.CreateNotePresenterImpl;
import masters.vlad.humeniuk.notesmvp.presentation.editcategory.presenter.EditCategoryPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.editcategory.presenter.EditCategoryPresenterImpl;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.presenter.EditNotePresenter;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.presenter.EditNotePresenterImpl;
import masters.vlad.humeniuk.notesmvp.presentation.main.presenter.MainPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.main.presenter.MainPresenterImpl;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.presenter.NotesListPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.presenter.NotesListPresenterImpl;

import static masters.vlad.humeniuk.notesmvp.di.modules.SchedulerModule.IO_SCHEDULER;
import static masters.vlad.humeniuk.notesmvp.di.modules.SchedulerModule.UI_SCHEDULER;

@Module
public class PresenterModule {

    @Provides
    @UserScope
    MainPresenter provideMainPresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                       CategoriesRepository categoriesRepository) {
        return new MainPresenterImpl(ioScheduler, categoriesRepository);
    }

    @Provides
    @UserScope
    NotesListPresenter provideNotesListPresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                 @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                 NotesRepository notesRepository) {
        return new NotesListPresenterImpl(ioScheduler, uiScheduler,
                notesRepository);
    }

    @Provides
    @UserScope
    CreateNotePresenter provideCreateNotePresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                   @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                   NotesRepository notesRepository,
                                                   CategoriesRepository categoriesRepository) {
        return new CreateNotePresenterImpl(ioScheduler, uiScheduler,
                notesRepository, categoriesRepository);
    }

    @Provides
    @UserScope
    EditNotePresenter provideEditNotePresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                               @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                               NotesRepository notesRepository,
                                               CategoriesRepository categoriesRepository) {
        return new EditNotePresenterImpl(ioScheduler, uiScheduler,
                notesRepository, categoriesRepository);
    }

    @Provides
    @UserScope
    CategoriesListPresenter provideCategoriesListPresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                           @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                           CategoriesRepository categoriesRepository) {
        return new CategoriesListPresenterImpl(ioScheduler, uiScheduler,
                categoriesRepository);
    }

    @Provides
    @UserScope
    CreateCategoryPresenter provideCreateCategoryPresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                           @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                           CategoriesRepository categoriesRepository) {
        return new CreateCategoryPresenterImpl(ioScheduler, uiScheduler, categoriesRepository);
    }

    @Provides
    @UserScope
    EditCategoryPresenter provideEditCategoryPresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                       @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                       CategoriesRepository categoriesRepository) {
        return new EditCategoryPresenterImpl(ioScheduler, uiScheduler,
                categoriesRepository);
    }
}
