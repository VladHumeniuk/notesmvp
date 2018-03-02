package masters.vlad.humeniuk.notesmvp.domain.repositories.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesmvp.database.dao.NoteDao;
import masters.vlad.humeniuk.notesmvp.database.entity.DbNote;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;
import masters.vlad.humeniuk.notesmvp.domain.mappers.NoteDbMapper;
import masters.vlad.humeniuk.notesmvp.domain.repositories.CategoriesRepository;
import masters.vlad.humeniuk.notesmvp.domain.repositories.NotesRepository;

public class NotesDbRepository implements NotesRepository {

    private NoteDao noteDao;

    private NoteDbMapper mapper;

    private CategoriesRepository categoriesRepository;

    public NotesDbRepository(NoteDao noteDao, NoteDbMapper mapper,
                             CategoriesRepository categoriesRepository) {
        this.noteDao = noteDao;
        this.mapper = mapper;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public Observable<Note> addNote(Note note) {
        return Observable.fromCallable(() ->
                noteDao.insert(mapper.mapBack(note)))
                .map(id -> {
                    note.setId(id);
                    return note;
                });
    }

    @Override
    public Observable<Boolean> deleteNote(Note note) {
        return Observable.fromCallable(() -> {
            noteDao.delete(mapper.mapBack(note));
            return true;
        });
    }

    @Override
    public Observable<Note> editNote(Note note) {
        return Observable.fromCallable(() -> {
            noteDao.update(mapper.mapBack(note));
            return note;
        });
    }

    @Override
    public Observable<List<Note>> getNotesListByCategory(Category category) {
        return category == null
                ? getNotesList()
                : Observable.fromCallable(() -> noteDao.findByCategory(category.getId()))
                .flatMapIterable(list -> list)
                .map(mapper::map)
                .map(note -> {
                    note.setCategory(category);
                    return note;
                })
                .toList()
                .toObservable();
    }

    private Observable<List<Note>> getNotesList() {
        return categoriesRepository.getCategoriesList()
                .map(this::convertToMap)
                .map(categories -> {
                    List<DbNote> dbNotes = noteDao.findAll();
                    List<Note> notes = new ArrayList<>();
                    for (DbNote dbNote : dbNotes) {
                        Note note = mapper.map(dbNote);
                        note.setCategory(categories.get(dbNote.getCategoryId()));
                        notes.add(note);
                    }
                    return notes;
                });
    }

    private Map<Long, Category> convertToMap(List<Category> categories) {
        Map<Long, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getId(), category);
        }
        return categoryMap;
    }
}
