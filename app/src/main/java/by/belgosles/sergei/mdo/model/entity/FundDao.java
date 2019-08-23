package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.belgosles.sergei.mdo.activity.ListStatements;

@Dao
public interface FundDao {

    @Query("SELECT * FROM Fund")
    List<Fund> getAllStatements();

    @Query("SELECT * FROM Fund WHERE id_fund = :id_fund")
    public Fund getSelectedFundForChange(long id_fund);

    @Insert
    void insert(Fund fund);

    @Update
    void update(Fund fund);

    @Delete
    void delete(Fund fund);

}
