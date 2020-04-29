package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// вид запаса
@Entity(tableName = "DICT_TYPE_STEM")
public class DictTypeStem {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_TYPE_STEM;
    private String NAME_TYPE_STEM;

    public Integer getCODE_TYPE_STEM() {
        return CODE_TYPE_STEM;
    }

    public void setCODE_TYPE_STEM(Integer CODE_TYPE_STEM) {
        this.CODE_TYPE_STEM = CODE_TYPE_STEM;
    }

    public String getNAME_TYPE_STEM() {
        return NAME_TYPE_STEM;
    }

    public void setNAME_TYPE_STEM(String NAME_TYPE_STEM) {
        this.NAME_TYPE_STEM = NAME_TYPE_STEM;
    }
}
