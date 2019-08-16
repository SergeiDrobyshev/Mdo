package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FundDao {

    @Query("SELECT * FROM Fund")
    List<Fund> getAllStatements();

    @Insert
    void insert(Fund fund);

    @Update
    void update(Fund fund);

    @Delete
    void delete(Fund fund);

}
