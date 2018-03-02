package masters.vlad.humeniuk.notesmvp.presentation.editnote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseActivity;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.view.EditNoteFragment;

public class EditNoteActivity extends BaseActivity {

    private static final String EXTRA_NOTE = "note";

    public static Intent newIntent(Context context, Note note) {
        Intent intent = new Intent(context, EditNoteActivity.class);
        intent.putExtra(EXTRA_NOTE, note);
        return intent;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showEditNoteFragment((Note) getIntent().getExtras().get(EXTRA_NOTE));
    }

    public void showEditNoteFragment(Note note) {
        BaseRouter.showFragment(EditNoteFragment.newInstance(note), true,
                R.id.fragment_container, getSupportFragmentManager());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }
}
