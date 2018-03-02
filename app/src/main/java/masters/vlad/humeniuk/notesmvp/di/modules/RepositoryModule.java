package masters.vlad.humeniuk.notesmvp.di.modules;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesmvp.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesmvp.database.dao.NoteDao;
import masters.vlad.humeniuk.notesmvp.di.scopes.UserScope;
import masters.vlad.humeniuk.notesmvp.domain.mappers.CategoryDbMapper;
import masters.vlad.humeniuk.notesmvp.domain.mappers.NoteDbMapper;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.domain.repositories.NotesRepository;
import masters.vlad.humeniuk.notesmvp.domain.repositories.implementation.CategoriesDbRepository;
import masters.vlad.humeniuk.notesmvp.domain.repositories.implementation.NotesDbRepository;

@Module
public class RepositoryModule {

    @Provides
    @UserScope
    CategoriesRepository provideCategoriesRepository(CategoryDao categoryDao,
                                                     CategoryDbMapper categoryDbMapper) {
        return new CategoriesDbRepository(categoryDao, categoryDbMapper);
    }

    @Provides
    @UserScope
    NotesRepository provideNotesRepository(NoteDao noteDao, NoteDbMapper noteDbMapper,
                                           CategoriesRepository categoriesRepository) {
        return new NotesDbRepository(noteDao, noteDbMapper, categoriesRepository);
    }
}
