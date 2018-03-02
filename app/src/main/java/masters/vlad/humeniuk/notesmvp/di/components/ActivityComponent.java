package masters.vlad.humeniuk.notesmvp.di.components;

import dagger.Component;
import masters.vlad.humeniuk.notesmvp.di.modules.ActivityModule;
import masters.vlad.humeniuk.notesmvp.di.modules.RepositoryModule;
import masters.vlad.humeniuk.notesmvp.di.modules.MapperModule;
import masters.vlad.humeniuk.notesmvp.di.modules.PresenterModule;
import masters.vlad.humeniuk.notesmvp.di.modules.SchedulerModule;
import masters.vlad.humeniuk.notesmvp.di.scopes.UserScope;
import masters.vlad.humeniuk.notesmvp.presentation.categories.view.CategoriesListFragment;
import masters.vlad.humeniuk.notesmvp.presentation.createcategory.CreateCategoryActivity;
import masters.vlad.humeniuk.notesmvp.presentation.createcategory.view.CreateCategoryFragment;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.CreateNoteActivity;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.view.CreateNoteFragment;
import masters.vlad.humeniuk.notesmvp.presentation.editcategory.EditCategoryActivity;
import masters.vlad.humeniuk.notesmvp.presentation.editcategory.view.EditCategoryFragment;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.EditNoteActivity;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.view.EditNoteFragment;
import masters.vlad.humeniuk.notesmvp.presentation.main.MainActivity;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.view.NotesListFragment;

@UserScope
@Component(dependencies = ApplicationComponent.class,
        modules = { RepositoryModule.class,
                ActivityModule.class,
                PresenterModule.class,
                MapperModule.class,
                SchedulerModule.class
})
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(CreateNoteActivity activity);

    void inject(EditNoteActivity activity);

    void inject(CreateCategoryActivity activity);

    void inject(EditCategoryActivity activity);

    void inject(NotesListFragment fragment);

    void inject(CreateNoteFragment fragment);

    void inject(EditNoteFragment fragment);

    void inject(CategoriesListFragment fragment);

    void inject(CreateCategoryFragment fragment);

    void inject(EditCategoryFragment fragment);
}
