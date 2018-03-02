package masters.vlad.humeniuk.notesmvp.presentation.categories.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import masters.vlad.humeniuk.notesmvp.R;
import masters.vlad.humeniuk.notesmvp.di.components.ActivityComponent;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseFragment;
import masters.vlad.humeniuk.notesmvp.presentation.base.BaseRouter;
import masters.vlad.humeniuk.notesmvp.presentation.base.list.ItemLongClickSelectedListener;
import masters.vlad.humeniuk.notesmvp.presentation.categories.presenter.CategoriesListPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.categories.view.list.CategoryAdapter;
import masters.vlad.humeniuk.notesmvp.presentation.createcategory.CreateCategoryActivity;
import masters.vlad.humeniuk.notesmvp.presentation.editcategory.EditCategoryActivity;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.view.NotesListFragment;

public class CategoriesListFragment extends BaseFragment implements CategoriesListView {

    public static final int REQUEST_ADD_CATEGORY = 13;
    public static final int REQUEST_EDIT_CATEGORY = 14;

    @BindView(R.id.categories_list)
    protected RecyclerView categoriesListView;

    @Inject
    protected CategoriesListPresenter presenter;

    private CategoryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static CategoriesListFragment newInstance() {

        Bundle args = new Bundle();

        CategoriesListFragment fragment = new CategoriesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        adapter = new CategoryAdapter(getContext(), listener);
        layoutManager = new LinearLayoutManager(getContext());
        categoriesListView.setLayoutManager(layoutManager);
        categoriesListView.setAdapter(adapter);
        presenter.init();
        return view;
    }

    @Override
    public void showCategories(List<Category> categories) {
        adapter.setData(categories);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_categories_list;
    }

    @OnClick(R.id.add_category)
    protected void onAddCategoryClick() {
        presenter.onAddCategoryClick();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            presenter.init();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showCategoryNotes(Category category) {
        BaseRouter.showFragment(NotesListFragment.newInstance(category), true,
                R.id.fragment_container, getActivity().getSupportFragmentManager());
    }

    @Override
    public void showAddCategory() {
        startActivityForResult(CreateCategoryActivity.newIntent(getActivity()),
                REQUEST_ADD_CATEGORY);
    }

    @Override
    public void showEditCategory(Category category) {
        startActivityForResult(EditCategoryActivity.newIntent(getActivity(), category),
                REQUEST_EDIT_CATEGORY);
    }

    private ItemLongClickSelectedListener<Category> listener =
            new ItemLongClickSelectedListener<Category>() {

                @Override
                public void onItemLongClick(Category entity) {
                    presenter.onCategoryLongClick(entity);
                }

                @Override
                public void onItemSelected(Category entity) {
                    presenter.onCategorySelected(entity);
                }
            };
}
