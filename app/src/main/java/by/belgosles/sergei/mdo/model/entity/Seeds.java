package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Fund.class, parentColumns = "id_fund", childColumns = "id_fund", onDelete = CASCADE, onUpdate = CASCADE))
public class Seeds {
    @PrimaryKey(autoGenerate = true)
    private long id_seeds;
    private int id_fund;

    private String id_species;
    private double seeds_square;
    private double amount_1ga;
    private int id_seeds_type;
    private int diameter_seeds;
    private int seeds_amount;

    public long getId_seeds() {
        return id_seeds;
    }

    public void setId_seeds(long id_seeds) {
        this.id_seeds = id_seeds;
    }

    public int getId_fund() {
        return id_fund;
    }

    public void setId_fund(int id_fund) {
        this.id_fund = id_fund;
    }

    public String getId_species() {
        return id_species;
    }

    public void setId_species(String id_species) {
        this.id_species = id_species;
    }

    public double getSeeds_square() {
        return seeds_square;
    }

    public void setSeeds_square(double seeds_square) {
        this.seeds_square = seeds_square;
    }

    public double getAmount_1ga() {
        return amount_1ga;
    }

    public void setAmount_1ga(double amount_1ga) {
        this.amount_1ga = amount_1ga;
    }

    public int getId_seeds_type() {
        return id_seeds_type;
    }

    public void setId_seeds_type(int id_seeds_type) {
        this.id_seeds_type = id_seeds_type;
    }

    public int getDiameter_seeds() {
        return diameter_seeds;
    }

    public void setDiameter_seeds(int diameter_seeds) {
        this.diameter_seeds = diameter_seeds;
    }

    public int getSeeds_amount() {
        return seeds_amount;
    }

    public void setSeeds_amount(int seeds_amount) {
        this.seeds_amount = seeds_amount;
    }

    public int getSeeds_n() {
        return seeds_n;
    }

    public void setSeeds_n(int seeds_n) {
        this.seeds_n = seeds_n;
    }

    private int seeds_n;
}
