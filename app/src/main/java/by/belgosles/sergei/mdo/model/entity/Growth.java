package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Fund.class, parentColumns = "id_fund", childColumns = "id_fund", onDelete = CASCADE, onUpdate = CASCADE))
public class Growth {

    @PrimaryKey(autoGenerate = true)
    private long id_growth;
    private long id_fund;

    private int id_species;
    private String amount;
    private String square_preserved;
    private String sostav;

    private String act_rad_n;
    private String act_rad_date;
    private String soil_rad_density; //плотность
    private String spec_activ_del;
    private String spec_activ_drov;

    public long getId_growth() {
        return id_growth;
    }

    public void setId_growth(long id_growth) {
        this.id_growth = id_growth;
    }

    public long getId_fund() {
        return id_fund;
    }

    public void setId_fund(long id_fund) {
        this.id_fund = id_fund;
    }

    public int getId_species() {
        return id_species;
    }

    public void setId_species(int id_species) {
        this.id_species = id_species;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSquare_preserved() {
        return square_preserved;
    }

    public void setSquare_preserved(String square_preserved) {
        this.square_preserved = square_preserved;
    }

    public String getSostav() {
        return sostav;
    }

    public void setSostav(String sostav) {
        this.sostav = sostav;
    }

    public String getAct_rad_n() {
        return act_rad_n;
    }

    public void setAct_rad_n(String act_rad_n) {
        this.act_rad_n = act_rad_n;
    }

    public String getAct_rad_date() {
        return act_rad_date;
    }

    public void setAct_rad_date(String act_rad_date) {
        this.act_rad_date = act_rad_date;
    }

    public String getSoil_rad_density() {
        return soil_rad_density;
    }

    public void setSoil_rad_density(String soil_rad_density) {
        this.soil_rad_density = soil_rad_density;
    }

    public String getSpec_activ_del() {
        return spec_activ_del;
    }

    public void setSpec_activ_del(String spec_activ_del) {
        this.spec_activ_del = spec_activ_del;
    }

    public String getSpec_activ_drov() {
        return spec_activ_drov;
    }

    public void setSpec_activ_drov(String spec_activ_drov) {
        this.spec_activ_drov = spec_activ_drov;
    }
}
