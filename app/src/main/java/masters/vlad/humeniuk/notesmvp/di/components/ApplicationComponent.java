package masters.vlad.humeniuk.notesmvp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import masters.vlad.humeniuk.notesmvp.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesmvp.database.dao.NoteDao;
import masters.vlad.humeniuk.notesmvp.di.modules.ApplicationModule;
import masters.vlad.humeniuk.notesmvp.di.modules.DbModule;

@Singleton
@Component(modules = { ApplicationModule.class, DbModule.class})
public interface ApplicationComponent {

    NoteDao getNoteDao();

    CategoryDao getCategoryDao();
}
