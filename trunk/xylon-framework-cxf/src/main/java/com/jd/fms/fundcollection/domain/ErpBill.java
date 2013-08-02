package com.jd.fms.fundcollection.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ErpBill implements Serializable{
	private static final long serialVersionUID = -556050289124778577L;

	private Integer id;

    private Integer orgCode;

    private String orgName;

    private Integer cityCode;

    private String cityName;

    private Integer stationCode;

    private String stationName;

    private Integer stationType;

    private String stationTypeName;

    private BigDecimal depositAmout;

    private Date depositTime;

    private Integer uuid;

    private String depositor;

    private String flag;

    private String isUpdate;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Integer orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getStationCode() {
        return stationCode;
    }

    public void setStationCode(Integer stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public Integer getStationType() {
        return stationType;
    }

    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }

    public String getStationTypeName() {
        return stationTypeName;
    }

    public void setStationTypeName(String stationTypeName) {
        this.stationTypeName = stationTypeName == null ? null : stationTypeName.trim();
    }

    public BigDecimal getDepositAmout() {
        return depositAmout;
    }

    public void setDepositAmout(BigDecimal depositAmout) {
        this.depositAmout = depositAmout;
    }

    public Date getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(Date depositTime) {
        this.depositTime = depositTime;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor == null ? null : depositor.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate == null ? null : isUpdate.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}