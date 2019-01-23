package by.belgosles.sergei.mdo.Db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Statement.class, parentColumns = "statement_id", childColumns = "st_id", onDelete = CASCADE))
public class StockandTaxCost {
    @PrimaryKey
    public long st_id;

    public String species;
    public String level_of_height;
    public String stock_del_kr;
    public String stock_del_sr;
    public String stock_del_m;
    public String stock_del_total;
    public String stock_drov;
    public String stock_liquid_total;
    public String stock_liquid_krone;
    public String stock_illiquid;
    public String stock_waste;
    public String stock_total;

    public String tax_del;
    public String tax_drov;
    public String tax_liquid_total;
    public String tax_liquid_krone;
    public String tax_illiquid;
    public String tax_waste;
    public String tax_total;

}
