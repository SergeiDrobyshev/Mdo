package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//категория леса
@Entity(tableName = "DICT_GR_FOREST")
public class DictGroupForest {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_GR_FOREST;
    private String NAME_GR_FOREST;
    private String SHORT_NAME_GR_FOREST;
    private String DATE_START;
    private String DATE_FINISH;

    public Integer getCODE_GR_FOREST() {
        return CODE_GR_FOREST;
    }

    public void setCODE_GR_FOREST(Integer CODE_GR_FOREST) {
        this.CODE_GR_FOREST = CODE_GR_FOREST;
    }

    public String getNAME_GR_FOREST() {
        return NAME_GR_FOREST;
    }

    public void setNAME_GR_FOREST(String NAME_GR_FOREST) {
        this.NAME_GR_FOREST = NAME_GR_FOREST;
    }

    public String getSHORT_NAME_GR_FOREST() {
        return SHORT_NAME_GR_FOREST;
    }

    public void setSHORT_NAME_GR_FOREST(String SHORT_NAME_GR_FOREST) {
        this.SHORT_NAME_GR_FOREST = SHORT_NAME_GR_FOREST;
    }

    public String getDATE_START() {
        return DATE_START;
    }

    public void setDATE_START(String DATE_START) {
        this.DATE_START = DATE_START;
    }

    public String getDATE_FINISH() {
        return DATE_FINISH;
    }

    public void setDATE_FINISH(String DATE_FINISH) {
        this.DATE_FINISH = DATE_FINISH;
    }
}
