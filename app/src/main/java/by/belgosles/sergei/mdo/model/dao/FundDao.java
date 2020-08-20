package by.belgosles.sergei.mdo.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.belgosles.sergei.mdo.model.entity.EnumTreesAmount;
import by.belgosles.sergei.mdo.model.entity.Fund;
import by.belgosles.sergei.mdo.model.entity.FundEnum;

@Dao
public interface FundDao {

    @Insert
    long insertFund(Fund fund);

    @Delete
    void delete(Fund fund);

    @Query("SELECT * FROM Fund")
    List<Fund> getAllStatements();

    @Query("SELECT * FROM Fund WHERE id_fund = :id_fund")
    public Fund getFundById(long id_fund);

    @Query("Update Fund SET " +
            "id_type_use = :id_type_use, " +
            "id_categ = :id_categ," +
            "year_fund = :year_fund," +
            "year_allot = :year_allot," +
            "kvart_n = :kvart_n," +
            "cut_area_n = :cut_area_n," +
            "tax_vydel_n = :tax_vydel_n," +
            "square = :square," +
            "id_hoz_section = :id_hoz_section," +
            "id_group_species = :id_group_species," +
            "id_type_cut = :id_type_cut," +
            "id_account_method = :id_account_method," +
            "id_clean_method = :id_clean_method," +
            "id_recovery_method = :id_recovery_method," +
            "sostav = :sostav," +
            "id_bonitet = :id_bonitet," +
            "id_forest_type = :id_forest_type," +
            "forest_cover = :forest_cover," +
            "id_soil = :id_soil," +
            "id_plaint_state = :id_plaint_state," +
            "age = :age," +
            "fullness = :fullness," +
            "ave_height = :ave_height," +
            "ave_diameter = :ave_diameter," +
            "comment = :comment " +
            "Where id_fund = :id_fund")
    void updateMdoStatementData(int id_type_use, int id_categ, String year_fund, String year_allot,
                                String kvart_n, String cut_area_n, String tax_vydel_n, String square,
                                int id_hoz_section, int id_group_species, int id_type_cut, int id_account_method, int id_clean_method, int id_recovery_method,
                                String sostav, int id_bonitet, int id_forest_type, String forest_cover, int id_soil, int id_plaint_state, String age,
                                String fullness, String ave_height, String ave_diameter, String comment, long id_fund);

    @Query("Update Fund SET " +
            "ave_whip = :whip " +
            "Where id_fund = :id_fund")
    void updateFund(String whip, long id_fund);

    @Query("Update Fund SET " +
            "filling_date = :filling_date," +
            "id_forestry = :id_forestry," +
            "id_tax_rate = :id_tax_rate," +       //todo change to int id_forestry
            "id_implementation = :id_implementation," +
            "inaccessibility = :inaccessibility," +
            "state_fund_forest = :state_fund_forest " +
            "WHERE id_fund = :id_fund")
    void updateFromCreateStatementActivity(String filling_date, String id_forestry, int id_tax_rate,
                                           int id_implementation, boolean inaccessibility, boolean state_fund_forest, long id_fund);

    @Query("Delete From Fund Where id_fund = :id_fund")
    void deleteNotSaveStatement(long id_fund);



    @Insert
    List<Long> insertListFundEnum(List<FundEnum> fundEnumlist);

    @Insert
    long insertFundEnum(FundEnum fundEnum);

    @Update
    void updateFundEnum(FundEnum fundEnum);

    @Query("Select * From FundEnum Where id_fund = :id_fund")
    List<FundEnum> getFundEnum(long id_fund);

    @Query("Delete From FundEnum Where id_fund = :id_fund")
    void deleteFundEnum(long id_fund);

    @Query("Select id_fund_enum From FundEnum Where (id_fund = :id_fund) AND (id_species = :id_species)")
    int getIdFundEnumBySpecies(long id_fund, int id_species);

    @Query("Update EnumTreesAmount SET " +
            "id_diameter = :diameter," +
            "amount_del = :del," +
            "amount_drov = :drov " +
            "Where id_fund_enum = :id_fund_enum")
    void updateEnumTreesAmount(int diameter, int del, int drov, int id_fund_enum);

}
