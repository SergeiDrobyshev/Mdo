package by.belgosles.sergei.mdo.model.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import by.belgosles.sergei.mdo.model.DictName;
import by.belgosles.sergei.mdo.model.entity.refs.DictKindUse;

@Dao
public interface DictsDao {

    @Query("Select CODE_SPECIES as id, NAME_SPECIES as value From DICT_SPECIES")
    List<DictName> getAllSpecies();

    @Query("Select CODE_ACC_METH as id, NAME_ACC_METH as value From DICT_ACC_METH")
    List<DictName> getAllAccMethods();

    @Query("Select CODE_BONIT as id, NAME_BONIT as value From DICT_BONIT")
    List<DictName> getAllBonitets();

    @Query("Select CODE_CL_METH as id, NAME_CL_METH as value From DICT_CL_METH")
    List<DictName> getAllCleanMethods();

    @Query("Select CODE_ECON as id, NAME_ECON as value From DICT_ECON")
    List<DictName> getAllSpeciesGroup();

    @Query("Select CODE_GR_FOREST as id, NAME_GR_FOREST as value From DICT_GR_FOREST")
    List<DictName> getAllGroupForest();

    @Query("Select CODE_KIND_USE as id, NAME_KIND_USE as value From DICT_KIND_USE")
    List<DictName> getAllKindUse();

    @Query("Select CODE_RGN_METH as id, NAME_RGN_METH as value From DICT_RGN_METH")
    List<DictName> getAllRecovMeths();

    @Query("Select CODE_SECT as id, NAME_SECT as value From DICT_SECTION")
    List<DictName> getAllSections();

    @Query("Select CODE_STATUS as id, NAME_STATUS as value From DICT_STATUS")
    List<DictName> getAllStatus();

    @Query("Select CODE_STR_SOIL as id, NAME_STR_SOIL as value  From DICT_STR_SOIL")
    List<DictName> getAllSoils();

    @Query("Select CODE_TYPE_FOR as id, NAME_TYPE_FOR as value From DICT_TYPE_FOR")
    List<DictName> getAllTypeFor();

    @Query("Select CODE_WCT_METH as id, NAME_WCT_METH as value From DICT_WCT_METH")
    List<DictName> getAllCutTypes();

    @Query("Select CODE_METH_REAL as id, NAME_METH_REAL as value From DICT_METH_REAL")
    List<DictName> getAllMethReal();

    @Query("Select CODE_RANK_TRF as id, NAME_RANK_TRF as value From DICT_RANK_TRF")
    List<DictName> getAllRankTrf();

    @Query("Select CODE_TRF_HEIGHT as id, NAME_TRF_HEIGHT as value From DICT_TRF_HEIGHT")
    List<DictName> getAllTrfHeight();
}
