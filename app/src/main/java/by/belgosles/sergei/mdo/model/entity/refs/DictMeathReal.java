package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//реализация
@Entity(tableName = "DICT_METH_REAL")
public class DictMeathReal {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_METH_REAL;
    private String NAME_METH_REAL;
    private String NAME_METH_REAL_SHORT;
    private String TERM_VAL;

    public Integer getCODE_METH_REAL() {
        return CODE_METH_REAL;
    }

    public void setCODE_METH_REAL(Integer CODE_METH_REAL) {
        this.CODE_METH_REAL = CODE_METH_REAL;
    }

    public String getNAME_METH_REAL() {
        return NAME_METH_REAL;
    }

    public void setNAME_METH_REAL(String NAME_METH_REAL) {
        this.NAME_METH_REAL = NAME_METH_REAL;
    }

    public String getNAME_METH_REAL_SHORT() {
        return NAME_METH_REAL_SHORT;
    }

    public void setNAME_METH_REAL_SHORT(String NAME_METH_REAL_SHORT) {
        this.NAME_METH_REAL_SHORT = NAME_METH_REAL_SHORT;
    }

    public String getTERM_VAL() {
        return TERM_VAL;
    }

    public void setTERM_VAL(String TERM_VAL) {
        this.TERM_VAL = TERM_VAL;
    }
}
