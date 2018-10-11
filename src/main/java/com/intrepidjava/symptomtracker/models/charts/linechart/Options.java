package com.intrepidjava.symptomtracker.models.charts.linechart;

public class Options {

    private Title title;

    public Options(){};

    public Options(Title title) {
        this.title = title;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
