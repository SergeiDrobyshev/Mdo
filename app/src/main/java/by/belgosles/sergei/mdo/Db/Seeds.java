package by.belgosles.sergei.mdo.Db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Statement.class, parentColumns = "statement_id", childColumns = "st_id", onDelete = CASCADE))
public class Seeds {
    @PrimaryKey
    public long st_id;

    public String species;
    public int diameter;
    public int seeds_amount;
    public int seeds_number;
}
