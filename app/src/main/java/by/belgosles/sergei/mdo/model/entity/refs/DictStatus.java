package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// состояние насаждений
@Entity(tableName = "DICT_STATUS")
public class DictStatus {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_STATUS;
    private String NAME_STATUS;
    private String NAME_STATUS_WOOD;

    public Integer getCODE_STATUS() {
        return CODE_STATUS;
    }

    public void setCODE_STATUS(Integer CODE_STATUS) {
        this.CODE_STATUS = CODE_STATUS;
    }

    public String getNAME_STATUS() {
        return NAME_STATUS;
    }

    public void setNAME_STATUS(String NAME_STATUS) {
        this.NAME_STATUS = NAME_STATUS;
    }

    public String getNAME_STATUS_WOOD() {
        return NAME_STATUS_WOOD;
    }

    public void setNAME_STATUS_WOOD(String NAME_STATUS_WOOD) {
        this.NAME_STATUS_WOOD = NAME_STATUS_WOOD;
    }
}
