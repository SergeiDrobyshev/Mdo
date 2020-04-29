package by.belgosles.sergei.mdo.model;

import android.content.Intent;

import java.util.ArrayList;

import butterknife.OnItemSelected;

public class DictName {
    private Integer id;
    private String value;

    public DictName(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
