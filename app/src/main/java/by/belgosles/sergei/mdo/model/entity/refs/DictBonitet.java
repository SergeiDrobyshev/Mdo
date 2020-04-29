package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//бонитет
@Entity(tableName = "DICT_BONIT")
public class DictBonitet {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_BONIT;
    private String NAME_BONIT;

    public Integer getCODE_BONIT() {
        return CODE_BONIT;
    }

    public void setCODE_BONIT(Integer CODE_BONIT) {
        this.CODE_BONIT = CODE_BONIT;
    }

    public String getNAME_BONIT() {
        return NAME_BONIT;
    }

    public void setNAME_BONIT(String NAME_BONIT) {
        this.NAME_BONIT = NAME_BONIT;
    }
}
