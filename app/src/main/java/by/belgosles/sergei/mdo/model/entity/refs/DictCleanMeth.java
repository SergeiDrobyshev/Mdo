package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//способ очистки
@Entity(tableName = "DICT_CL_METH")
public class DictCleanMeth {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_CL_METH;
    private String NAME_CL_METH;
    private Integer TERM_VAL;

    public Integer getCODE_CL_METH() {
        return CODE_CL_METH;
    }

    public void setCODE_CL_METH(Integer CODE_CL_METH) {
        this.CODE_CL_METH = CODE_CL_METH;
    }

    public String getNAME_CL_METH() {
        return NAME_CL_METH;
    }

    public void setNAME_CL_METH(String NAME_CL_METH) {
        this.NAME_CL_METH = NAME_CL_METH;
    }

    public Integer getTERM_VAL() {
        return TERM_VAL;
    }

    public void setTERM_VAL(Integer TERM_VAL) {
        this.TERM_VAL = TERM_VAL;
    }
}
