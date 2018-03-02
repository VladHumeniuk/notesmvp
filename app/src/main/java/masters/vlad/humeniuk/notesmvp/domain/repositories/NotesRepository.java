package masters.vlad.humeniuk.notesmvp.domain.repositories;

import java.util.List;

import io.reactivex.Observable;
import masters.vlad.humeniuk.notesmvp.domain.entity.Category;
import masters.vlad.humeniuk.notesmvp.domain.entity.Note;

public interface NotesRepository {

    Observable<Note> addNote(Note note);

    Observable<Boolean> deleteNote(Note note);

    Observable<Note> editNote(Note note);

    Observable<List<Note>> getNotesListByCategory(Category category);
}
