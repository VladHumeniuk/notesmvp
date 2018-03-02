package masters.vlad.humeniuk.notesmvp.di.modules;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesmvp.di.scopes.UserScope;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseActivity;

@Module
public class ActivityModule {

    private BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @UserScope
    BaseActivity provideBaseActivity() {
        return baseActivity;
    }
}
