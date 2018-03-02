package masters.vlad.humeniuk.notesmvp.presentation.createcategory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesmvp.presentation.createcategory.view.CreateCategoryFragment;

public class CreateCategoryActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateCategoryActivity.class);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showCreateCategoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }

    private void showCreateCategoryFragment() {
        BaseRouter.showFragment(CreateCategoryFragment.newInstance(), true,
                R.id.fragment_container, getSupportFragmentManager());
    }
}
