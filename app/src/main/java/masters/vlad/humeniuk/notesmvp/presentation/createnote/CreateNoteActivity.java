package masters.vlad.humeniuk.notesmvp.presentation.createnote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.view.CreateNoteFragment;

public class CreateNoteActivity extends BaseActivity {

    private static final String EXTRA_CATEGORY = "category";

    public static Intent newIntent(Context context) {
        return newIntent(context, null);
    }

    public static Intent newIntent(Context context, Category category) {
        Intent intent = new Intent(context, CreateNoteActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        return intent;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showCreateNoteFragment((Category) getIntent().getExtras().get(EXTRA_CATEGORY));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }

    private void showCreateNoteFragment(Category category) {
        BaseRouter.showFragment(CreateNoteFragment.newInstance(category), true,
                R.id.fragment_container, getSupportFragmentManager());
    }
}
