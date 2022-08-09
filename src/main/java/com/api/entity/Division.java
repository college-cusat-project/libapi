package com.api.entity;

import java.util.List;

public class Division {

    private String divisionId;
    private String divisionName;
    private List<SubDivision> subDivisionList;

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public List<SubDivision> getSubDivisionList() {
        return subDivisionList;
    }

    public void setSubDivisionList(List<SubDivision> subDivisionList) {
        this.subDivisionList = subDivisionList;
    }

    @Override
    public String toString() {
        return " [divisionID=" + divisionId + ", divisionName=" + divisionName + "]";
    }

}
