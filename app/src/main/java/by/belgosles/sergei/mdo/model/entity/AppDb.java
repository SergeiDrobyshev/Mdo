package by.belgosles.sergei.mdo.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Fund.class, FundEnum.class, Growth.class, Seeds.class, EnumTreesAmount.class, StockTaxCost.class, FundEnumModelTrees.class}, version = 2, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract FundDao getstatementDao();

    public static final Migration Migration_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE FundEnum ADD COLUMN test_field INTEGER DEFAULT 0 NOT NULL");
        }
    };
}
