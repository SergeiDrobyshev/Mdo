package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//группа пород
@Entity(tableName = "DICT_ECON")
public class DictEcon {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_ECON;
    private String NAME_ECON;

    public Integer getCODE_ECON() {
        return CODE_ECON;
    }

    public void setCODE_ECON(Integer CODE_ECON) {
        this.CODE_ECON = CODE_ECON;
    }

    public String getNAME_ECON() {
        return NAME_ECON;
    }

    public void setNAME_ECON(String NAME_ECON) {
        this.NAME_ECON = NAME_ECON;
    }
}
