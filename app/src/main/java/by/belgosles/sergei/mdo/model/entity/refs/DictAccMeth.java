package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import by.belgosles.sergei.mdo.model.entity.Fund;

// метод учета
@Entity(tableName = "DICT_ACC_METH")
public class DictAccMeth {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_ACC_METH;
    private String NAME_ACC_METH;
    private Integer CODE_ACC_METH_WT;

    public Integer getCODE_ACC_METH() {
        return CODE_ACC_METH;
    }

    public void setCODE_ACC_METH(Integer CODE_ACC_METH) {
        this.CODE_ACC_METH = CODE_ACC_METH;
    }

    public String getNAME_ACC_METH() {
        return NAME_ACC_METH;
    }

    public void setNAME_ACC_METH(String NAME_ACC_METH) {
        this.NAME_ACC_METH = NAME_ACC_METH;
    }

    public Integer getCODE_ACC_METH_WT() {
        return CODE_ACC_METH_WT;
    }

    public void setCODE_ACC_METH_WT(Integer CODE_ACC_METH_WT) {
        this.CODE_ACC_METH_WT = CODE_ACC_METH_WT;
    }
}
