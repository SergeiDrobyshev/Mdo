package by.belgosles.sergei.mdo.model.entity.refs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//породы
@Entity(tableName = "DICT_SPECIES")//, foreignKeys = {
        //@ForeignKey(entity = FundEnum.class, childColumns = "CODE_SPECIES", parentColumns = "id_species"),
       // @ForeignKey(entity = Seeds.class,childColumns = "CODE_SPECIES", parentColumns = "id_species"),
        //@ForeignKey(entity = StockTaxCost.class,childColumns = "CODE_SPECIES", parentColumns = "id_species"),
       // @ForeignKey(entity = Growth.class,childColumns = "CODE_SPECIES", parentColumns = "id_species")})

public class DictSpecies {
    @PrimaryKey(autoGenerate = true)
    private Integer CODE_SPECIES;

    private String NAME_SPECIES;
    private String NAME_SPECIES_SHORT;
    private Integer CODE_ECON;

    public int getCODE_SPECIES() {
        return CODE_SPECIES;
    }

    public void setCODE_SPECIES(int CODE_SPECIES) {
        this.CODE_SPECIES = CODE_SPECIES;
    }

    public String getNAME_SPECIES() {
        return NAME_SPECIES;
    }

    public void setNAME_SPECIES(String NAME_SPECIES) {
        this.NAME_SPECIES = NAME_SPECIES;
    }

    public String getNAME_SPECIES_SHORT() {
        return NAME_SPECIES_SHORT;
    }

    public void setNAME_SPECIES_SHORT(String NAME_SPECIES_SHORT) {
        this.NAME_SPECIES_SHORT = NAME_SPECIES_SHORT;
    }

    public int getCODE_ECON() {
        return CODE_ECON;
    }

    public void setCODE_ECON(int CODE_ECON) {
        this.CODE_ECON = CODE_ECON;
    }
}
