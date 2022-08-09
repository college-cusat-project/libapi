package com.api.entity;

import java.util.List;

public class Circle {

    private String circleId;
    private String circleName;
    private List<Division> divisionList;

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public List<Division> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<Division> divisionList) {
        this.divisionList = divisionList;
    }

    @Override
    public String toString() {
        return " [circleID=" + circleId + ",circleName=" + circleName + "]";
    }
}
