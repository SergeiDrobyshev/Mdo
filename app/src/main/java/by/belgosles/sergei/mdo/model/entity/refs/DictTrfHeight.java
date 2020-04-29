package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// разряд высот
@Entity(tableName = "DICT_TRF_HEIGHT")
public class DictTrfHeight {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_TRF_HEIGHT;
    private String NAME_TRF_HEIGHT;

    public Integer getCODE_TRF_HEIGHT() {
        return CODE_TRF_HEIGHT;
    }

    public void setCODE_TRF_HEIGHT(Integer CODE_TRF_HEIGHT) {
        this.CODE_TRF_HEIGHT = CODE_TRF_HEIGHT;
    }

    public String getNAME_TRF_HEIGHT() {
        return NAME_TRF_HEIGHT;
    }

    public void setNAME_TRF_HEIGHT(String NAME_TRF_HEIGHT) {
        this.NAME_TRF_HEIGHT = NAME_TRF_HEIGHT;
    }
}
