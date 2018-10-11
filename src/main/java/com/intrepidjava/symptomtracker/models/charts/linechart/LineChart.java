package com.intrepidjava.symptomtracker.models.charts.linechart;

import java.util.List;

public class LineChart {

    private String type;

    private Data data;

    private Options options;

    public LineChart() {};

    public LineChart(String type, Data data, Options options) {
        this.type = type;
        this.data = data;
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}
