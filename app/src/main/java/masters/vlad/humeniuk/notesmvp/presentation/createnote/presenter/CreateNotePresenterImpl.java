package masters.vlad.humeniuk.notesmvp.presentation.createnote.presenter;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.domain.repositories.NotesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.createnote.view.CreateNoteView;

public class CreateNotePresenterImpl extends RxPresenter implements CreateNotePresenter {

    private CreateNoteView view;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private NotesRepository notesRepository;

    private CategoriesRepository categoriesRepository;

    public CreateNotePresenterImpl(Scheduler ioScheduler, Scheduler uiScheduler,
                                   NotesRepository notesRepository,
                                   CategoriesRepository categoriesRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.notesRepository = notesRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void onSaveNote(String title, String description, Category category) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            view.showEmptyFieldsError();
        } else {
            Note note = new Note();
            note.setTitle(title);
            note.setDescription(description);
            Date date = Calendar.getInstance().getTime();
            note.setDateCreated(date);
            note.setDateLastEdit(date);
            note.setCategory(category);
            saveNote(note);
        }
    }

    @Override
    public void setView(CreateNoteView view) {
        this.view = view;
    }

    @Override
    public void setDefaultCategory(Category category) {
        loadCategories(category);
    }

    private void saveNote(Note note) {
        subscribe(0, notesRepository.addNote(note)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> view.backToMain()));
    };

    private void loadCategories(Category defaultCategory) {
        subscribe(1, categoriesRepository.getCategoriesList()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(list -> {
                    view.showCategories(list);
                    if (defaultCategory != null) {
                        view.showDefaultCategory(defaultCategory);
                    }
                }));
    }
}
