package masters.vlad.humeniuk.notesmvp.presentation;

import android.app.Application;

import masters.vlad.humeniuk.notesmvp.di.components.ApplicationComponent;
import masters.vlad.humeniuk.notesmvp.di.components.DaggerApplicationComponent;
import masters.vlad.humeniuk.notesmvp.di.modules.ApplicationModule;
import masters.vlad.humeniuk.notesmvp.di.modules.DbModule;

public class NotesApplication extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dbModule(new DbModule(this))
                .build();

    }
}
