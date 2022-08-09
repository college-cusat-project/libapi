package com.api.entity;

import java.util.List;

public class SubDivision {

    private String subDivisionId;
    private String subDivisionName;
    private List<Pss> pssList;

    public String getSubDivisionId() {
        return subDivisionId;
    }

    public void setSubDivisionId(String subDivisionId) {
        this.subDivisionId = subDivisionId;
    }

    public String getSubDivisionName() {
        return subDivisionName;
    }

    public void setSubDivisionName(String subDivisionName) {
        this.subDivisionName = subDivisionName;
    }

    public List<Pss> getPssList() {
        return pssList;
    }

    public void setPssList(List<Pss> pssList) {
        this.pssList = pssList;
    }

    @Override
    public String toString() {
        return " [subDivisionID=" + subDivisionId + ", SubdivisionName=" + subDivisionName + "]";
    }

}
