package com.intrepidjava.symptomtracker.models.charts.linechart;

public class Title {

    private boolean dispaly;

    private String text;

    public Title(){};

    public Title(boolean dispaly, String text) {
        this.dispaly = dispaly;
        this.text = text;
    }

    public boolean isDispaly() {
        return dispaly;
    }

    public void setDispaly(boolean dispaly) {
        this.dispaly = dispaly;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
