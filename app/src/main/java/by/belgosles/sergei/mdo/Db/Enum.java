package by.belgosles.sergei.mdo.Db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Statement.class, parentColumns = "statement_id", childColumns = "st_id", onDelete = CASCADE))
public class Enum {
    @PrimaryKey
    public long st_id;

    public String species;
    public int diameter;
    public int value_del;
    public int value_drov;
    public int sum_del;
    public int sum_drov;

    public int heights_level;

// модельные деревья и данные замеров высот

}

