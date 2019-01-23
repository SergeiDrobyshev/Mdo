package by.belgosles.sergei.mdo.Db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Statement.class, Enum.class, Growth.class, Seeds.class}, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract StatementDao getstatementDao();

}
