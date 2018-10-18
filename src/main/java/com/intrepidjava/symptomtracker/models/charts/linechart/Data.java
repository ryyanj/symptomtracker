package com.intrepidjava.symptomtracker.models.charts.linechart;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private List<Integer> labels;

    private List<DataSet> datasets;

    public Data(){};

    public Data(List<Integer> labels, List<DataSet> datasets) {
        this.labels = labels;
        this.datasets = datasets;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public List<DataSet> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<DataSet> datasets) {
        this.datasets = datasets;
    }

}
