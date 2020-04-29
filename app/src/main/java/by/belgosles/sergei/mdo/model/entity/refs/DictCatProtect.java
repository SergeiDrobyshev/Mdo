package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// категория защитности
@Entity(tableName = "DICT_CAT_PROTECTION")
public class DictCatProtect {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_CAT_PROTECTION;
    private String DATE_START;
    private String DATE_FINISH;
    private String NAME_CAT_PROTECTION;
    private Integer CODE_GR_FOREST;

    public Integer getCODE_CAT_PROTECTION() {
        return CODE_CAT_PROTECTION;
    }

    public void setCODE_CAT_PROTECTION(Integer CODE_CAT_PROTECTION) {
        this.CODE_CAT_PROTECTION = CODE_CAT_PROTECTION;
    }

    public String getDATE_START() {
        return DATE_START;
    }

    public void setDATE_START(String DATE_START) {
        this.DATE_START = DATE_START;
    }

    public String getDATE_FINISH() {
        return DATE_FINISH;
    }

    public void setDATE_FINISH(String DATE_FINISH) {
        this.DATE_FINISH = DATE_FINISH;
    }

    public String getNAME_CAT_PROTECTION() {
        return NAME_CAT_PROTECTION;
    }

    public void setNAME_CAT_PROTECTION(String NAME_CAT_PROTECTION) {
        this.NAME_CAT_PROTECTION = NAME_CAT_PROTECTION;
    }

    public Integer getCODE_GR_FOREST() {
        return CODE_GR_FOREST;
    }

    public void setCODE_GR_FOREST(Integer CODE_GR_FOREST) {
        this.CODE_GR_FOREST = CODE_GR_FOREST;
    }
}
