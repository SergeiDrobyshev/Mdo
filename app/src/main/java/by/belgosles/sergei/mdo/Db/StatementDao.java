package by.belgosles.sergei.mdo.Db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StatementDao {

    @Query("SELECT * FROM statement")
    List<Statement> getAll();

   /* @Query("SELECT species FROM enum WHERE st_id = :id")
    List<String> getById(long id);*/

    @Insert
    void insert(Statement statement);

    /*@Insert
    void insert(Enum enum1);

    @Insert
    void insert(Growth growth);

    @Insert
    void insert(Seeds seeds);

    @Update
    void update(Statement statement);
*/
    @Delete
    void delete(Statement statement);



}
