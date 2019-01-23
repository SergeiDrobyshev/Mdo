package by.belgosles.sergei.mdo.Db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Statement {
    @PrimaryKey(autoGenerate = true)
    public long statement_id;

    public String forestry;
    public String tax_rate;
    public String filling_date;
    public String implementation;
    public boolean inaccessibility; // труднодоступность
    public boolean land_of_state_forest_fund;


    public String type_of_use, forest_category, forest_fund_year, allotment_year, square_area;
    public  int quarter_number, cutting_area_number, tax_area_number;

    public String econSection, group_of_species, type_of_cutting, accounting_method, cleaning_method, recovery_method;

    public String structure, bonitet, type_of_forest, forest_cover/*покров*/, soil, plantings_state;

    public int age, fullness, average_height, average_diameter;
    public String note;


    public String average_amount_of_whip;

    public int act_rad_nomer;
    public String act_rad_date;
    public String soil_pol_rad; //плотность
    public String spec_activity_del;
    public String spec_activity_drov;


    public String square_seeds;
    public int amount_seeds;
}
