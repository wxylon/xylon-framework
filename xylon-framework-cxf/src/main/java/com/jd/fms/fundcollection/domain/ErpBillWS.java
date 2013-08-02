package com.jd.fms.fundcollection.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * webservcie传入的erp实收bean
 * @author wangx
 * @date 2012-5-14
 */
public class ErpBillWS implements Serializable{
	private static final long serialVersionUID = -556050289124778577L;
	/**站点编号*/
    private Integer stationCode;
    /**站点名称*/
    private String stationName;
    /**站点属性编号*/
    private Integer stationType;
    /**站点属性名称*/
    private String stationTypeName;
    /**实收金额*/
    private BigDecimal depositAmout;
    /**存入时间*/
    private String depositTime;
    /**唯一标识*/
    private String uuid;
    /**城市编号*/
    private Integer cityCode;
    /**城市名称*/
    private String cityName;
	/**区域编号*/
    private Integer orgCode;
    /**区域名称*/
    private String orgName;
    /**撤销标识*/
    private String flag;

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

    public String getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(String depositTime) {
    	this.depositTime = depositTime == null ? null : depositTime.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
    	this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}