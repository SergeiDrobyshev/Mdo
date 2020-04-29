package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DICT_WCT_METH")
public class DictCutMeth {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_WCT_METH;
    private String NAME_WCT_METH;
    private Integer CODE_KIND_USE;

    public Integer getCODE_WCT_METH() {
        return CODE_WCT_METH;
    }

    public void setCODE_WCT_METH(Integer CODE_WCT_METH) {
        this.CODE_WCT_METH = CODE_WCT_METH;
    }

    public String getNAME_WCT_METH() {
        return NAME_WCT_METH;
    }

    public void setNAME_WCT_METH(String NAME_WCT_METH) {
        this.NAME_WCT_METH = NAME_WCT_METH;
    }

    public Integer getCODE_KIND_USE() {
        return CODE_KIND_USE;
    }

    public void setCODE_KIND_USE(Integer CODE_KIND_USE) {
        this.CODE_KIND_USE = CODE_KIND_USE;
    }

    public String getTERM_VAL() {
        return TERM_VAL;
    }

    public void setTERM_VAL(String TERM_VAL) {
        this.TERM_VAL = TERM_VAL;
    }

    private String TERM_VAL;


}
