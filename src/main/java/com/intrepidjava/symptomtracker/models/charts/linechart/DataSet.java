package com.intrepidjava.symptomtracker.models.charts.linechart;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataSet {

    private List<Integer> data;

    private String label;

    private String borderColor;

    private boolean fill;

    private boolean hidden;

    public DataSet(){};

    public DataSet(List<Integer> data, String label, String borderColor, boolean fill) {
        this.data = data;
        this.label = label;
        this.borderColor = borderColor;
        this.fill = fill;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
