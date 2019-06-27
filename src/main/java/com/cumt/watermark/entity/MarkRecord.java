package com.cumt.watermark.entity;

public class MarkRecord {
    int id;
    String dataName;
    String executeTime;
    String firmName;
    String firmSign;
    String firmIcon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmSign() {
        return firmSign;
    }

    public void setFirmSign(String firmSign) {
        this.firmSign = firmSign;
    }

    public String getFirmIcon() {
        return firmIcon;
    }

    public void setFirmIcon(String firmIcon) {
        this.firmIcon = firmIcon;
    }
}
