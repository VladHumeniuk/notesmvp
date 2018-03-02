package masters.vlad.humeniuk.notesmvp.di.modules;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesmvp.domain.mappers.CategoryDbMapper;
import masters.vlad.humeniuk.notesmvp.domain.mappers.NoteDbMapper;

@Module
public class MapperModule {

    @Provides
    CategoryDbMapper provideCategoryDbMapper() {
        return new CategoryDbMapper();
    }

    @Provides
    NoteDbMapper provideNoteDbMapper() {
        return new NoteDbMapper();
    }
}
