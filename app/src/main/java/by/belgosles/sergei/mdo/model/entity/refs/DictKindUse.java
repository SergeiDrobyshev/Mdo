package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//вид пользования
@Entity(tableName = "DICT_KIND_USE")
public class DictKindUse {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_KIND_USE;
    private String NAME_KIND_USE;

    public Integer getCODE_KIND_USE() {
        return CODE_KIND_USE;
    }

    public void setCODE_KIND_USE(Integer CODE_KIND_USE) {
        this.CODE_KIND_USE = CODE_KIND_USE;
    }

    public String getNAME_KIND_USE() {
        return NAME_KIND_USE;
    }

    public void setNAME_KIND_USE(String NAME_KIND_USE) {
        this.NAME_KIND_USE = NAME_KIND_USE;
    }



}
