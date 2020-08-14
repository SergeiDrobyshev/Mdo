package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

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
        version = 6, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract FundDao getstatementDao();

    public abstract DictsDao getDictsDao();


    /*public static final Migration Migration_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE FundEnum ADD COLUMN test_field INTEGER DEFAULT 0 NOT NULL");
        }
    };*/

}
