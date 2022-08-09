package com.api.entity;

public class Pss {

    private String pssId;
    private String pssName;

    public String getPssId() {
        return pssId;
    }

    public void setPssId(String pssId) {
        this.pssId = pssId;
    }

    public String getPssName() {
        return pssName;
    }

    public void setPssName(String pssName) {
        this.pssName = pssName;
    }

    @Override
    public String toString() {
        return " [pssID=" + pssId + ", pssName=" + pssName + "]";
    }
}

