package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// разряд такс
@Entity(tableName = "DICT_RANK_TRF")
public class DictRankTrf {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_RANK_TRF;
    private String NAME_RANK_TRF;

    public int getCODE_RANK_TRF() {
        return CODE_RANK_TRF;
    }

    public void setCODE_RANK_TRF(int CODE_RANK_TRF) {
        this.CODE_RANK_TRF = CODE_RANK_TRF;
    }

    public String getNAME_RANK_TRF() {
        return NAME_RANK_TRF;
    }

    public void setNAME_RANK_TRF(String NAME_RANK_TRF) {
        this.NAME_RANK_TRF = NAME_RANK_TRF;
    }
}
