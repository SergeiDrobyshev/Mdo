package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Fund.class, parentColumns = "id_fund", childColumns = "id_fund", onDelete = CASCADE, onUpdate = CASCADE))
public class StockTaxCost {
    @PrimaryKey(autoGenerate = true)
    private long id_stock_tax;
    private int id_fund;

    private String id_species;
    private String id_level_height;
    private String del_large_st;
    private String del_ave_st;
    private String del_sm_st;
    private String del_total_st;
    private String drov_st;
    private String liquid_total_st;
    private String crown_st;
    private String brushwood_st;
    private String waste_st;
    private String total_st;

    private String del_cost;
    private String drov_cost;
    private String liquid_total_cost;
    private String crown_cost;
    private String brushwood_cost;
    private String waste_cost;

    public long getId_stock_tax() {
        return id_stock_tax;
    }

    public void setId_stock_tax(long id_stock_tax) {
        this.id_stock_tax = id_stock_tax;
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

    public String getId_level_height() {
        return id_level_height;
    }

    public void setId_level_height(String id_level_height) {
        this.id_level_height = id_level_height;
    }

    public String getDel_large_st() {
        return del_large_st;
    }

    public void setDel_large_st(String del_large_st) {
        this.del_large_st = del_large_st;
    }

    public String getDel_ave_st() {
        return del_ave_st;
    }

    public void setDel_ave_st(String del_ave_st) {
        this.del_ave_st = del_ave_st;
    }

    public String getDel_sm_st() {
        return del_sm_st;
    }

    public void setDel_sm_st(String del_sm_st) {
        this.del_sm_st = del_sm_st;
    }

    public String getDel_total_st() {
        return del_total_st;
    }

    public void setDel_total_st(String del_total_st) {
        this.del_total_st = del_total_st;
    }

    public String getDrov_st() {
        return drov_st;
    }

    public void setDrov_st(String drov_st) {
        this.drov_st = drov_st;
    }

    public String getLiquid_total_st() {
        return liquid_total_st;
    }

    public void setLiquid_total_st(String liquid_total_st) {
        this.liquid_total_st = liquid_total_st;
    }

    public String getCrown_st() {
        return crown_st;
    }

    public void setCrown_st(String crown_st) {
        this.crown_st = crown_st;
    }

    public String getBrushwood_st() {
        return brushwood_st;
    }

    public void setBrushwood_st(String brushwood_st) {
        this.brushwood_st = brushwood_st;
    }

    public String getWaste_st() {
        return waste_st;
    }

    public void setWaste_st(String waste_st) {
        this.waste_st = waste_st;
    }

    public String getTotal_st() {
        return total_st;
    }

    public void setTotal_st(String total_st) {
        this.total_st = total_st;
    }

    public String getDel_cost() {
        return del_cost;
    }

    public void setDel_cost(String del_cost) {
        this.del_cost = del_cost;
    }

    public String getDrov_cost() {
        return drov_cost;
    }

    public void setDrov_cost(String drov_cost) {
        this.drov_cost = drov_cost;
    }

    public String getLiquid_total_cost() {
        return liquid_total_cost;
    }

    public void setLiquid_total_cost(String liquid_total_cost) {
        this.liquid_total_cost = liquid_total_cost;
    }

    public String getCrown_cost() {
        return crown_cost;
    }

    public void setCrown_cost(String crown_cost) {
        this.crown_cost = crown_cost;
    }

    public String getBrushwood_cost() {
        return brushwood_cost;
    }

    public void setBrushwood_cost(String brushwood_cost) {
        this.brushwood_cost = brushwood_cost;
    }

    public String getWaste_cost() {
        return waste_cost;
    }

    public void setWaste_cost(String waste_cost) {
        this.waste_cost = waste_cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    private String total_cost;

}
