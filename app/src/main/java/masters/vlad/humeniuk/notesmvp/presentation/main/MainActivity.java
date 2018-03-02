package masters.vlad.humeniuk.notesmvp.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;

import javax.inject.Inject;

import butterknife.BindView;
import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesmvp.presentation.categories.view.CategoriesListFragment;
import masters.vlad.humeniuk.notesmvp.presentation.main.presenter.MainPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.view.NotesListFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    protected BottomNavigationView bottomNavigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Inject
    protected MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.init();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        bottomNavigationView.setSelectedItemId(R.id.action_notes);
    }

    public void showNotesList() {
        BaseRouter.clearBackStack(getSupportFragmentManager());
        BaseRouter.showFragment(NotesListFragment.newInstance(), true,
                R.id.fragment_container, getSupportFragmentManager());
    }

    public void showCategories() {
        BaseRouter.clearBackStack(getSupportFragmentManager());
        BaseRouter.showFragment(CategoriesListFragment.newInstance(), true,
                R.id.fragment_container, getSupportFragmentManager());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.action_notes: {
                        showNotesList();
                        return true;
                    }
                    case R.id.action_categories: {
                        showCategories();
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            };
}
