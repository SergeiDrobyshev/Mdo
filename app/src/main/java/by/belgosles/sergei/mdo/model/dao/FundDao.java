package by.belgosles.sergei.mdo.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.belgosles.sergei.mdo.model.entity.Fund;

@Dao
public interface FundDao {

    @Query("SELECT * FROM Fund")
    List<Fund> getAllStatements();

    @Query("SELECT * FROM Fund WHERE id_fund = :id_fund")
    public Fund getFundById(long id_fund);

    @Query("Select last_insert_rowid()")
    public int getLastInsertRowId();

    @Update
    void update(Fund fund);

    @Insert
    long insert(Fund fund);

    @Delete
    void delete(Fund fund);

}
