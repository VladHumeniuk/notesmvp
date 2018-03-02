package masters.vlad.humeniuk.notesmvp.presentation.editnote.presenter;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.domain.repositories.NotesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.editnote.view.EditNoteView;

public class EditNotePresenterImpl extends RxPresenter implements EditNotePresenter {

    private EditNoteView view;

    private Note note;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private NotesRepository notesRepository;

    private CategoriesRepository categoriesRepository;

    public EditNotePresenterImpl(Scheduler ioScheduler, Scheduler uiScheduler,
                                 NotesRepository notesRepository,
                                 CategoriesRepository categoriesRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.notesRepository = notesRepository;;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void setNote(Note note) {
        this.note = note;
        view.showNote(note);
        loadCategories(note.getCategory());
    }

    @Override
    public void saveNote(String title, String description, Category category) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            view.showEmptyFieldsError();
        } else {
            note.setTitle(title);
            note.setDescription(description);
            Date date = Calendar.getInstance().getTime();
            note.setDateLastEdit(date);
            note.setCategory(category);
            saveNote(note);
        }
    }

    @Override
    public void setView(EditNoteView view) {
        this.view = view;
    }

    @Override
    public void deleteNote() {
        subscribe(1, notesRepository.deleteNote(note)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> view.backToMain()));
    }

    private void saveNote(Note note) {
        subscribe(0, notesRepository.editNote(note)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(result -> view.backToMain()));
    }

    private void loadCategories(Category selectedCategory) {
        subscribe(2, categoriesRepository.getCategoriesList()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(list -> {
                    view.showCategories(list, selectedCategory);
                }));
    }
}
