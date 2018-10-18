package com.intrepidjava.symptomtracker.models.charts.linechart;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChartData {

    private List<Date> labels;
    private List<DataSet> dataSets;

    public List<Date> getLabels() {
        return labels;
    }

    public void setLabels(List<Date> labels) {
        this.labels = labels;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<DataSet> dataSets) {
        this.dataSets = dataSets;
    }
}
