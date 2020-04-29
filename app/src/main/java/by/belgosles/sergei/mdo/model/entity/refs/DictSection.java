package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// хоз. секция
@Entity(tableName = "DICT_SECTION")
public class DictSection {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_SECT;
    private String NAME_SECT;
    private Integer CODE_ECON;

    public Integer getCODE_SECT() {
        return CODE_SECT;
    }

    public void setCODE_SECT(Integer CODE_SECT) {
        this.CODE_SECT = CODE_SECT;
    }

    public String getNAME_SECT() {
        return NAME_SECT;
    }

    public void setNAME_SECT(String NAME_SECT) {
        this.NAME_SECT = NAME_SECT;
    }

    public Integer getCODE_ECON() {
        return CODE_ECON;
    }

    public void setCODE_ECON(Integer CODE_ECON) {
        this.CODE_ECON = CODE_ECON;
    }
}
