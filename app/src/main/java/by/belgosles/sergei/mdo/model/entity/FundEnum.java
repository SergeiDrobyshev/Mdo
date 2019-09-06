package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Fund.class, parentColumns = "id_fund", childColumns = "id_fund", onDelete = CASCADE, onUpdate = CASCADE),
       // @ForeignKey(entity = DictSpecies.class, childColumns = "id_species", parentColumns = "CODE_SPECIES")
})
public class FundEnum {
    @PrimaryKey(autoGenerate = true)
    private long id_fund_enum;

    private String id_species;
    private int id_height_level;
    private int id_fund;

    public long getId_fund_enum() {
        return id_fund_enum;
    }

    public void setId_fund_enum(long id_fund_enum) {
        this.id_fund_enum = id_fund_enum;
    }

    public String getId_species() {
        return id_species;
    }

    public void setId_species(String id_species) {
        this.id_species = id_species;
    }

    public int getId_height_level() {
        return id_height_level;
    }

    public void setId_height_level(int id_height_level) {
        this.id_height_level = id_height_level;
    }

    public int getId_fund() {
        return id_fund;
    }

    public void setId_fund(int id_fund) {
        this.id_fund = id_fund;
    }

    // модельные деревья и данные замеров высот

}

