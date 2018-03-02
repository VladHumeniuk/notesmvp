package masters.vlad.humeniuk.notesmvp.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesmvp.presentation.NotesApplication;

@Module
public class ApplicationModule {

    private final NotesApplication application;

    public ApplicationModule(NotesApplication application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    NotesApplication provideNotesApplication() {
        return application;
    }

}
