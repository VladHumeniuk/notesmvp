package masters.vlad.humeniuk.notesmvp.domain.repositories.implementation;

import java.util.List;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesmvp.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesmvp.database.entity.DbCategory;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.mappers.CategoryDbMapper;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;

public class CategoriesDbRepository implements CategoriesRepository {

    private CategoryDao categoryDao;

    private CategoryDbMapper mapper;

    public CategoriesDbRepository(CategoryDao categoryDao, CategoryDbMapper mapper) {
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    @Override
    public Observable<Category> addCategory(Category category) {
        return Observable.fromCallable(() ->
                categoryDao.insert(mapper.mapBack(category)))
                .map(id -> {
                    category.setId(id);
                    return category;
                });
    }

    @Override
    public Observable<List<Category>> getCategoriesList() {
        return Observable.fromCallable(() -> categoryDao.findAll())
                .flatMapIterable(list -> list)
                .map(mapper::map)
                .toList()
                .toObservable();
    }

    @Override
    public Observable<Boolean> deleteCategory(Category category) {
        return Observable.fromCallable(() -> {
            categoryDao.delete(mapper.mapBack(category));
            return true;
        });
    }

    @Override
    public Observable<Category> editCategory(Category category) {
        return Observable.fromCallable(() -> {
            categoryDao.update(mapper.mapBack(category));
            return category;
        });
    }

    @Override
    public Observable<Long> init() {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setName("No category");
        dbCategory.setId(0);
        dbCategory.setColor("#aaaaff");
        return Observable.fromCallable(() -> categoryDao.findAll())
                .map(List::size)
                .map(size -> size == 0 ? categoryDao.insert(dbCategory) : 0l);
    }
}
