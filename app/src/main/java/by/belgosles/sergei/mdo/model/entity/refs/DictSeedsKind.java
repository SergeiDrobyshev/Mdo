package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//вид семенников
@Entity(tableName = "DICT_KIND_SEEDS")
public class DictSeedsKind {

    @PrimaryKey(autoGenerate = true)
    private Integer CODE_KIND_SEEDS;
    private String NAME_KIND_SEEDS;

    public Integer getCODE_KIND_SEEDS() {
        return CODE_KIND_SEEDS;
    }

    public void setCODE_KIND_SEEDS(Integer CODE_KIND_SEEDS) {
        this.CODE_KIND_SEEDS = CODE_KIND_SEEDS;
    }

    public String getNAME_KIND_SEEDS() {
        return NAME_KIND_SEEDS;
    }

    public void setNAME_KIND_SEEDS(String NAME_KIND_SEEDS) {
        this.NAME_KIND_SEEDS = NAME_KIND_SEEDS;
    }

}
