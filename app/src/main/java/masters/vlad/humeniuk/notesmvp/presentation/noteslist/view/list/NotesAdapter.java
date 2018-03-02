package masters.vlad.humeniuk.notesmvp.presentation.noteslist.view.list;

import android.content.Context;
import android.view.View;

import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.BaseAdapter;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.BaseViewHolder;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.ItemSelectedListener;

public class NotesAdapter extends BaseAdapter<Note> {

    private ItemSelectedListener<Note> listener;

    public NotesAdapter(Context context, ItemSelectedListener<Note> listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected int getItemLayout(int viewType) {
        return R.layout.simple_list_item;
    }

    @Override
    protected BaseViewHolder createViewHolder(View itemView, int viewType) {
        return new NoteViewHolder(itemView, listener);
    }
}
