package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Fund.class, FundEnum.class, Growth.class, Seeds.class, EnumTreesAmount.class, StockTaxCost.class, FundEnumModelTrees.class}, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract FundDao getstatementDao();

}
