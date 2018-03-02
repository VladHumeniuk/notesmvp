package masters.vlad.humeniuk.notesmvp.presentation.noteslist.presenter;

import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.domain.repositories.NotesRepository;
import masters.vlad.humeniuk.notesmvp.presentation.base.RxPresenter;
import masters.vlad.humeniuk.notesmvp.presentation.noteslist.view.NotesListView;

public class NotesListPresenterImpl extends RxPresenter implements NotesListPresenter {

    private NotesListView view;

    private Scheduler ioScheduler;

    private Scheduler uiScheduler;

    private NotesRepository notesRepository;

    private Category category;

    public NotesListPresenterImpl(Scheduler ioScheduler, Scheduler uiScheduler,
                                  NotesRepository notesRepository) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.notesRepository = notesRepository;
    }

    public void setView(NotesListView notesListView) {
        this.view = notesListView;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void loadNotes() {
        subscribe(0, notesRepository.getNotesListByCategory(category)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(view::showNotesList));
    }

    @Override
    public void onNoteSelected(Note note) {
        view.openNotesDetails(note);
    }

    @Override
    public void onCreateNote() {
        view.openCreateNote(category);
    }
}
