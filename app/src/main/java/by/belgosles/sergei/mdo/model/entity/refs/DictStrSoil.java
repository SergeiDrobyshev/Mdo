package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// почва
@Entity(tableName = "DICT_STR_SOIL")
public class DictStrSoil {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_STR_SOIL;
    private String NAME_STR_SOIL;

    public Integer getCODE_STR_SOIL() {
        return CODE_STR_SOIL;
    }

    public void setCODE_STR_SOIL(Integer CODE_STR_SOIL) {
        this.CODE_STR_SOIL = CODE_STR_SOIL;
    }

    public String getNAME_STR_SOIL() {
        return NAME_STR_SOIL;
    }

    public void setNAME_STR_SOIL(String NAME_STR_SOIL) {
        this.NAME_STR_SOIL = NAME_STR_SOIL;
    }
}
