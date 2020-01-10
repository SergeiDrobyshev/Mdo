package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Fund {
    @PrimaryKey(autoGenerate = true)
    public long id_fund;

    //todo change to int
    private String id_forestry;

    public int getId_leshoz() {
        return id_leshoz;
    }

    public void setId_leshoz(int id_leshoz) {
        this.id_leshoz = id_leshoz;
    }

    private int id_leshoz;
    private int id_tax_rate;
    private String filling_date;

    private int id_implementation;
    private boolean inaccessibility; // труднодоступность
    private boolean state_fund_forest;

    private int id_type_use, id_categ;
    private String year_fund, year_allot, square;
    private String kvart_n, cut_area_n, tax_vydel_n;

    private int id_hoz_section, id_group_species, id_type_cut, id_account_method, id_clean_method, id_recovery_method;

    private String sostav, forest_cover/*покров*/;
    private int id_bonitet, id_forest_type,  id_soil, id_plaint_state;

    private String  age, fullness, ave_height, ave_diameter;
    private String comment;

    private String ave_whip;

    public long getId_fund() {
        return id_fund;
    }

    public void setId_fund(long id_fund) {
        this.id_fund = id_fund;
    }

    public String getId_forestry() {
        return id_forestry;
    }

    public void setId_forestry(String id_forestry) {
        this.id_forestry = id_forestry;
    }

    public int getId_tax_rate() {
        return id_tax_rate;
    }

    public void setId_tax_rate(int id_tax_rate) {
        this.id_tax_rate = id_tax_rate;
    }

    public String getFilling_date() {
        return filling_date;
    }

    public void setFilling_date(String filling_date) {
        this.filling_date = filling_date;
    }

    public int getId_implementation() {
        return id_implementation;
    }

    public void setId_implementation(int id_implementation) {
        this.id_implementation = id_implementation;
    }

    public boolean isInaccessibility() {
        return inaccessibility;
    }

    public void setInaccessibility(boolean inaccessibility) {
        this.inaccessibility = inaccessibility;
    }

    public boolean isState_fund_forest() {
        return state_fund_forest;
    }

    public void setState_fund_forest(boolean state_fund_forest) {
        this.state_fund_forest = state_fund_forest;
    }

    public int getId_type_use() {
        return id_type_use;
    }

    public void setId_type_use(int id_type_use) {
        this.id_type_use = id_type_use;
    }

    public int getId_categ() {
        return id_categ;
    }

    public void setId_categ(int id_categ) {
        this.id_categ = id_categ;
    }

    public String getYear_fund() {
        return year_fund;
    }

    public void setYear_fund(String year_fund) {
        this.year_fund = year_fund;
    }

    public String getYear_allot() {
        return year_allot;
    }

    public void setYear_allot(String year_allot) {
        this.year_allot = year_allot;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getKvart_n() {
        return kvart_n;
    }

    public void setKvart_n(String kvart_n) {
        this.kvart_n = kvart_n;
    }

    public String getCut_area_n() {
        return cut_area_n;
    }

    public void setCut_area_n(String cut_area_n) {
        this.cut_area_n = cut_area_n;
    }

    public String getTax_vydel_n() {
        return tax_vydel_n;
    }

    public void setTax_vydel_n(String tax_vydel_n) {
        this.tax_vydel_n = tax_vydel_n;
    }

    public int getId_hoz_section() {
        return id_hoz_section;
    }

    public void setId_hoz_section(int id_hoz_section) {
        this.id_hoz_section = id_hoz_section;
    }

    public int getId_group_species() {
        return id_group_species;
    }

    public void setId_group_species(int id_group_species) {
        this.id_group_species = id_group_species;
    }

    public int getId_type_cut() {
        return id_type_cut;
    }

    public void setId_type_cut(int id_type_cut) {
        this.id_type_cut = id_type_cut;
    }

    public int getId_account_method() {
        return id_account_method;
    }

    public void setId_account_method(int id_account_method) {
        this.id_account_method = id_account_method;
    }

    public int getId_clean_method() {
        return id_clean_method;
    }

    public void setId_clean_method(int id_clean_method) {
        this.id_clean_method = id_clean_method;
    }

    public int getId_recovery_method() {
        return id_recovery_method;
    }

    public void setId_recovery_method(int id_recovery_method) {
        this.id_recovery_method = id_recovery_method;
    }

    public String getSostav() {
        return sostav;
    }

    public void setSostav(String sostav) {
        this.sostav = sostav;
    }

    public int getId_bonitet() {
        return id_bonitet;
    }

    public void setId_bonitet(int id_bonitet) {
        this.id_bonitet = id_bonitet;
    }

    public int getId_forest_type() {
        return id_forest_type;
    }

    public void setId_forest_type(int id_forest_type) {
        this.id_forest_type = id_forest_type;
    }

    public String getForest_cover() {
        return forest_cover;
    }

    public void setForest_cover(String forest_cover) {
        this.forest_cover = forest_cover;
    }

    public int getId_soil() {
        return id_soil;
    }

    public void setId_soil(int id_soil) {
        this.id_soil = id_soil;
    }

    public int getId_plaint_state() {
        return id_plaint_state;
    }

    public void setId_plaint_state(int id_plaint_state) {
        this.id_plaint_state = id_plaint_state;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFullness() {
        return fullness;
    }

    public void setFullness(String fullness) {
        this.fullness = fullness;
    }

    public String getAve_height() {
        return ave_height;
    }

    public void setAve_height(String ave_height) {
        this.ave_height = ave_height;
    }

    public String getAve_diameter() {
        return ave_diameter;
    }

    public void setAve_diameter(String ave_diameter) {
        this.ave_diameter = ave_diameter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAve_whip() {
        return ave_whip;
    }

    public void setAve_whip(String ave_whip) {
        this.ave_whip = ave_whip;
    }
}
