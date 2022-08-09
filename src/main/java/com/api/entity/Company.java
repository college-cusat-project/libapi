package com.api.entity;

import java.util.List;

public class Company {

    private String companyId;
    private String companyName;
    private List<Circle> circleList;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Circle> getCircleList() {
        return circleList;
    }

    public void setCircleList(List<Circle> circleList) {
        this.circleList = circleList;
    }

    @Override
    public String toString() {
        return " [companyID=" + companyId + ", companyName=" + companyName + "]";
    }
}
