package masters.vlad.humeniuk.notesmvp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import masters.vlad.humeniuk.notesmvp.database.dao.CategoryDao;
import masters.vlad.humeniuk.notesmvp.database.dao.NoteDao;
import masters.vlad.humeniuk.notesmvp.database.entity.DbCategory;
import masters.vlad.humeniuk.notesmvp.database.entity.DbNote;

@Database(entities = { DbNote.class, DbCategory.class },
        version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public static final String DB_NAME = "notesMvpDatabase.db";

    public abstract NoteDao getNoteDao();

    public abstract CategoryDao getCategoryDao();
}
