package by.belgosles.sergei.mdo.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = FundEnum.class, parentColumns = "id_fund_enum", childColumns = "id_fund_enum", onDelete = CASCADE, onUpdate = CASCADE))
public class FundEnumModelTrees {
    @PrimaryKey(autoGenerate = true)
    private int id_fund_enum_model;

    private int id_fund_enum;

    private int number;
    private int diameter;

    public int getId_fund_enum_model() {
        return id_fund_enum_model;
    }

    public void setId_fund_enum_model(int id_fund_enum_model) {
        this.id_fund_enum_model = id_fund_enum_model;
    }

    public int getId_fund_enum() {
        return id_fund_enum;
    }

    public void setId_fund_enum(int id_fund_enum) {
        this.id_fund_enum = id_fund_enum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private int height;

}
