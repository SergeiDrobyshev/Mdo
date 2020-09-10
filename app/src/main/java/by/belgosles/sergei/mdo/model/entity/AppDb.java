package by.belgosles.sergei.mdo.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import by.belgosles.sergei.mdo.model.dao.DictsDao;
import by.belgosles.sergei.mdo.model.dao.FundDao;
import by.belgosles.sergei.mdo.model.entity.refs.DictAccMeth;
import by.belgosles.sergei.mdo.model.entity.refs.DictBonitet;
import by.belgosles.sergei.mdo.model.entity.refs.DictCatProtect;
import by.belgosles.sergei.mdo.model.entity.refs.DictCleanMeth;
import by.belgosles.sergei.mdo.model.entity.refs.DictCutMeth;
import by.belgosles.sergei.mdo.model.entity.refs.DictEcon;
import by.belgosles.sergei.mdo.model.entity.refs.DictGroupForest;
import by.belgosles.sergei.mdo.model.entity.refs.DictKindUse;
import by.belgosles.sergei.mdo.model.entity.refs.DictMeathReal;
import by.belgosles.sergei.mdo.model.entity.refs.DictRankTrf;
import by.belgosles.sergei.mdo.model.entity.refs.DictRgnMeth;
import by.belgosles.sergei.mdo.model.entity.refs.DictSection;
import by.belgosles.sergei.mdo.model.entity.refs.DictSeedsKind;
import by.belgosles.sergei.mdo.model.entity.refs.DictSpecies;
import by.belgosles.sergei.mdo.model.entity.refs.DictStatus;
import by.belgosles.sergei.mdo.model.entity.refs.DictStrSoil;
import by.belgosles.sergei.mdo.model.entity.refs.DictTrfHeight;
import by.belgosles.sergei.mdo.model.entity.refs.DictTypeForest;
import by.belgosles.sergei.mdo.model.entity.refs.DictTypeStem;

@Database(entities = {
        Fund.class, FundEnum.class, Growth.class, Seeds.class, EnumTreesAmount.class, StockTaxCost.class, FundEnumModelTrees.class, DictSpecies.class,
        DictRankTrf.class, DictMeathReal.class, DictAccMeth.class, DictBonitet.class, DictCatProtect.class, DictCleanMeth.class, DictEcon.class,
        DictGroupForest.class, DictSeedsKind.class, DictKindUse.class, DictRgnMeth.class, DictSection.class, DictStatus.class, DictStrSoil.class,
        DictTrfHeight.class, DictTypeForest.class, DictTypeStem.class, DictCutMeth.class
},
        version = 10, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract FundDao getstatementDao();

    public abstract DictsDao getDictsDao();


    public static final Migration Migration_9_10 = new Migration(9, 10) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
                //создание новой таблицы
            database.execSQL("CREATE TABLE Growth_new (id_growth INTEGER Not Null ,id_fund INTEGER Not Null, id_species INTEGER Not Null, amount TEXT, square_preserved TEXT, sostav TEXT, act_rad_n TEXT, act_rad_date TEXT, soil_rad_density TEXT, spec_activ_del TEXT, spec_activ_drov TEXT, PRIMARY KEY (id_growth) , FOREIGN KEY (id_fund) REFERENCES Fund(id_fund) ON DELETE CASCADE ON UPDATE CASCADE)");
                // копируем данные во временную таблицу
            database.execSQL("INSERT INTO Growth_new (id_growth, id_fund, id_species, amount, square_preserved, sostav, act_rad_n, act_rad_date, soil_rad_density, spec_activ_del, spec_activ_drov) SELECT id_growth, id_fund, id_species, amount, square_preserved, sostav, act_rad_n, act_rad_date, soil_rad_density, spec_activ_del, spec_activ_drov FROM Growth");
                // Удаляем старую таблицу
            database.execSQL("DROP TABLE Growth");
                // Измените имя таблицы на правильное
            database.execSQL("ALTER TABLE Growth_new RENAME TO Growth");
        }
    };

}
