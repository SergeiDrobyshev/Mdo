package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//способ восстановления
@Entity(tableName = "DICT_RGN_METH")
public class DictRgnMeth {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_RGN_METH;
    private String NAME_RGN_METH;

    public Integer getCODE_RGN_METH() {
        return CODE_RGN_METH;
    }

    public void setCODE_RGN_METH(Integer CODE_RGN_METH) {
        this.CODE_RGN_METH = CODE_RGN_METH;
    }

    public String getNAME_RGN_METH() {
        return NAME_RGN_METH;
    }

    public void setNAME_RGN_METH(String NAME_RGN_METH) {
        this.NAME_RGN_METH = NAME_RGN_METH;
    }
}
