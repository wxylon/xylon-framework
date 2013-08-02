package com.jd.fms.fundcollection.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * webservcie�����erpʵ��bean
 * @author wangx
 * @date 2012-5-14
 */
public class ErpBillWS implements Serializable{
	private static final long serialVersionUID = -556050289124778577L;
	/**վ����*/
    private Integer stationCode;
    /**վ������*/
    private String stationName;
    /**վ�����Ա��*/
    private Integer stationType;
    /**վ����������*/
    private String stationTypeName;
    /**ʵ�ս��*/
    private BigDecimal depositAmout;
    /**����ʱ��*/
    private String depositTime;
    /**Ψһ��ʶ*/
    private String uuid;
    /**���б��*/
    private Integer cityCode;
    /**��������*/
    private String cityName;
	/**������*/
    private Integer orgCode;
    /**��������*/
    private String orgName;
    /**������ʶ*/
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