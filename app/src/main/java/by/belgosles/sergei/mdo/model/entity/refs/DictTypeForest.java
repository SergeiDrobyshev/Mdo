package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// тип леса
@Entity(tableName = "DICT_TYPE_FOR")
public class DictTypeForest {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_TYPE_FOR;
    private String NAME_TYPE_FOR;
    private String NAME_TYPE_FOR_SHORT;

    public Integer getCODE_TYPE_FOR() {
        return CODE_TYPE_FOR;
    }

    public void setCODE_TYPE_FOR(Integer CODE_TYPE_FOR) {
        this.CODE_TYPE_FOR = CODE_TYPE_FOR;
    }

    public String getNAME_TYPE_FOR() {
        return NAME_TYPE_FOR;
    }

    public void setNAME_TYPE_FOR(String NAME_TYPE_FOR) {
        this.NAME_TYPE_FOR = NAME_TYPE_FOR;
    }

    public String getNAME_TYPE_FOR_SHORT() {
        return NAME_TYPE_FOR_SHORT;
    }

    public void setNAME_TYPE_FOR_SHORT(String NAME_TYPE_FOR_SHORT) {
        this.NAME_TYPE_FOR_SHORT = NAME_TYPE_FOR_SHORT;
    }
}
