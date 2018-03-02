package masters.vlad.humeniuk.notesmvp.presentation.categories.view.list;

import android.content.Context;
import android.view.View;

import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.BaseAdapter;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.BaseViewHolder;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.ItemLongClickSelectedListener;

public class CategoryAdapter extends BaseAdapter<Category> {

    private ItemLongClickSelectedListener<Category> listener;

    public CategoryAdapter(Context context,
                           ItemLongClickSelectedListener<Category> listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected int getItemLayout(int viewType) {
        return R.layout.category_list_item;
    }

    @Override
    protected BaseViewHolder createViewHolder(View itemView, int viewType) {
        return new CategoryViewHolder(itemView, listener);
    }
}
