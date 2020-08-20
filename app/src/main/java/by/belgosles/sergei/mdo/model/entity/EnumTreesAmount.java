package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = FundEnum.class, parentColumns = "id_fund_enum",childColumns = "id_fund_enum", onDelete = CASCADE, onUpdate = CASCADE))
public class EnumTreesAmount {

    @PrimaryKey(autoGenerate = true)
    private int id_trees_amount_enum;

    private int id_diameter;
    private String amount_del;
    private String amount_drov;

    private long id_fund_enum;

    public int getId_diameter() {
        return id_diameter;
    }

    public void setId_diameter(int id_diameter) {
        this.id_diameter = id_diameter;
    }

    public String getAmount_del() {
        return amount_del;
    }

    public void setAmount_del(String amount_del) {
        this.amount_del = amount_del;
    }

    public String getAmount_drov() {
        return amount_drov;
    }

    public void setAmount_drov(String amount_drov) {
        this.amount_drov = amount_drov;
    }

    public long getId_fund_enum() {
        return id_fund_enum;
    }

    public void setId_fund_enum(long id_fund_enum) {
        this.id_fund_enum = id_fund_enum;
    }

    public int getId_trees_amount_enum() {
        return id_trees_amount_enum;
    }

    public void setId_trees_amount_enum(int id_trees_amount_enum) {
        this.id_trees_amount_enum = id_trees_amount_enum;
    }
}
