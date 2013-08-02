package com.jd.whs;

import java.util.Date;

public class WareVO
{
	//organization table information
	private int organizationId;
	private int organizationCode;
	private String organizationName;
	private String organizationArea;
	private int organizationDelFlag;
	private Date organizationCreatedDate;
	private String organizationCreatedBy;
	private Date organizationUpdateDate;
	private String organizationUpdateBy;
	private String organizationChangeIp;
	private String organizationVersion;
	
	//delivery center table information
	private int deliveryId;
	private int deliveryCode; 
	private String deliveryName;
	private String deliveryCity;
	private int deliveryDelFlag;
	private Date deliveryCreatedDate;
	private String deliveryCreatedBy;
	private Date deliveryUpdateDate;
	private String deliveryUpdateBy;
	private String deliveryChangeIp;
	private String deliveryVersion;
	
	//ware house table information
	private int wareId;
	private int wareErpCode;
	private String wareType;
	private String wareErpName;
	private String wareOrderCode;
	private String wareWmsCode;
	private String wareWmsName;
	private String wareWmsType;
	private String wareWmsVersion;
	private String wareAddress;
	private String wareContact;
	private String wareTelephoneNumber;
	private String wareMobileNumber;
	private String wareVrcontact;
	private String wareVrTelephoneNumber;
	private String wareVrMobileNumber;
	private int wareDelFlag;
	private Date wareCreatedDate;
	private String wareCreatedBy;
	private Date wareUpdatedDate;
	private String wareUpdatedBy;
	private String wareChangeIp;
	private String wareVersion;
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	public int getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(int organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationArea() {
		return organizationArea;
	}
	public void setOrganizationArea(String organizationArea) {
		this.organizationArea = organizationArea;
	}
	public int getOrganizationDelFlag() {
		return organizationDelFlag;
	}
	public void setOrganizationDelFlag(int organizationDelFlag) {
		this.organizationDelFlag = organizationDelFlag;
	}
	public Date getOrganizationCreatedDate() {
		return organizationCreatedDate;
	}
	public void setOrganizationCreatedDate(Date organizationCreatedDate) {
		this.organizationCreatedDate = organizationCreatedDate;
	}
	public String getOrganizationCreatedBy() {
		return organizationCreatedBy;
	}
	public void setOrganizationCreatedBy(String organizationCreatedBy) {
		this.organizationCreatedBy = organizationCreatedBy;
	}
	public Date getOrganizationUpdateDate() {
		return organizationUpdateDate;
	}
	public void setOrganizationUpdateDate(Date organizationUpdateDate) {
		this.organizationUpdateDate = organizationUpdateDate;
	}
	public String getOrganizationUpdateBy() {
		return organizationUpdateBy;
	}
	public void setOrganizationUpdateBy(String organizationUpdateBy) {
		this.organizationUpdateBy = organizationUpdateBy;
	}
	public String getOrganizationChangeIp() {
		return organizationChangeIp;
	}
	public void setOrganizationChangeIp(String organizationChangeIp) {
		this.organizationChangeIp = organizationChangeIp;
	}
	public String getOrganizationVersion() {
		return organizationVersion;
	}
	public void setOrganizationVersion(String organizationVersion) {
		this.organizationVersion = organizationVersion;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public int getDeliveryCode() {
		return deliveryCode;
	}
	public void setDeliveryCode(int deliveryCode) {
		this.deliveryCode = deliveryCode;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryCity() {
		return deliveryCity;
	}
	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}
	public int getDeliveryDelFlag() {
		return deliveryDelFlag;
	}
	public void setDeliveryDelFlag(int deliveryDelFlag) {
		this.deliveryDelFlag = deliveryDelFlag;
	}
	public Date getDeliveryCreatedDate() {
		return deliveryCreatedDate;
	}
	public void setDeliveryCreatedDate(Date deliveryCreatedDate) {
		this.deliveryCreatedDate = deliveryCreatedDate;
	}
	public String getDeliveryCreatedBy() {
		return deliveryCreatedBy;
	}
	public void setDeliveryCreatedBy(String deliveryCreatedBy) {
		this.deliveryCreatedBy = deliveryCreatedBy;
	}
	public Date getDeliveryUpdateDate() {
		return deliveryUpdateDate;
	}
	public void setDeliveryUpdateDate(Date deliveryUpdateDate) {
		this.deliveryUpdateDate = deliveryUpdateDate;
	}
	public String getDeliveryUpdateBy() {
		return deliveryUpdateBy;
	}
	public void setDeliveryUpdateBy(String deliveryUpdateBy) {
		this.deliveryUpdateBy = deliveryUpdateBy;
	}
	public String getDeliveryChangeIp() {
		return deliveryChangeIp;
	}
	public void setDeliveryChangeIp(String deliveryChangeIp) {
		this.deliveryChangeIp = deliveryChangeIp;
	}
	public String getDeliveryVersion() {
		return deliveryVersion;
	}
	public void setDeliveryVersion(String deliveryVersion) {
		this.deliveryVersion = deliveryVersion;
	}
	public int getWareId() {
		return wareId;
	}
	public void setWareId(int wareId) {
		this.wareId = wareId;
	}
	public int getWareErpCode() {
		return wareErpCode;
	}
	public void setWareErpCode(int wareErpCode) {
		this.wareErpCode = wareErpCode;
	}
	public String getWareErpName() {
		return wareErpName;
	}
	public void setWareErpName(String wareErpName) {
		this.wareErpName = wareErpName;
	}
	public String getWareOrderCode() {
		return wareOrderCode;
	}
	public void setWareOrderCode(String wareOrderCode) {
		this.wareOrderCode = wareOrderCode;
	}
	public String getWareWmsCode() {
		return wareWmsCode;
	}
	public void setWareWmsCode(String wareWmsCode) {
		this.wareWmsCode = wareWmsCode;
	}
	public String getWareWmsName() {
		return wareWmsName;
	}
	public void setWareWmsName(String wareWmsName) {
		this.wareWmsName = wareWmsName;
	}
	public String getWareWmsType() {
		return wareWmsType;
	}
	public void setWareWmsType(String wareWmsType) {
		this.wareWmsType = wareWmsType;
	}
	public String getWareWmsVersion() {
		return wareWmsVersion;
	}
	public void setWareWmsVersion(String wareWmsVersion) {
		this.wareWmsVersion = wareWmsVersion;
	}
	public String getWareAddress() {
		return wareAddress;
	}
	public void setWareAddress(String wareAddress) {
		this.wareAddress = wareAddress;
	}
	public String getWareContact() {
		return wareContact;
	}
	public void setWareContact(String wareContact) {
		this.wareContact = wareContact;
	}
	public String getWareTelephoneNumber() {
		return wareTelephoneNumber;
	}
	public void setWareTelephoneNumber(String wareTelephoneNumber) {
		this.wareTelephoneNumber = wareTelephoneNumber;
	}
	public String getWareMobileNumber() {
		return wareMobileNumber;
	}
	public void setWareMobileNumber(String wareMobileNumber) {
		this.wareMobileNumber = wareMobileNumber;
	}
	public String getWareVrcontact() {
		return wareVrcontact;
	}
	public void setWareVrcontact(String wareVrcontact) {
		this.wareVrcontact = wareVrcontact;
	}
	public String getWareVrTelephoneNumber() {
		return wareVrTelephoneNumber;
	}
	public void setWareVrTelephoneNumber(String wareVrTelephoneNumber) {
		this.wareVrTelephoneNumber = wareVrTelephoneNumber;
	}
	public String getWareVrMobileNumber() {
		return wareVrMobileNumber;
	}
	public void setWareVrMobileNumber(String wareVrMobileNumber) {
		this.wareVrMobileNumber = wareVrMobileNumber;
	}
	public int getWareDelFlag() {
		return wareDelFlag;
	}
	public void setWareDelFlag(int wareDelFlag) {
		this.wareDelFlag = wareDelFlag;
	}
	public Date getWareCreatedDate() {
		return wareCreatedDate;
	}
	public void setWareCreatedDate(Date wareCreatedDate) {
		this.wareCreatedDate = wareCreatedDate;
	}
	public String getWareCreatedBy() {
		return wareCreatedBy;
	}
	public void setWareCreatedBy(String wareCreatedBy) {
		this.wareCreatedBy = wareCreatedBy;
	}
	public Date getWareUpdatedDate() {
		return wareUpdatedDate;
	}
	public void setWareUpdatedDate(Date wareUpdatedDate) {
		this.wareUpdatedDate = wareUpdatedDate;
	}
	public String getWareUpdatedBy() {
		return wareUpdatedBy;
	}
	public void setWareUpdatedBy(String wareUpdatedBy) {
		this.wareUpdatedBy = wareUpdatedBy;
	}
	public String getWareChangeIp() {
		return wareChangeIp;
	}
	public void setWareChangeIp(String wareChangeIp) {
		this.wareChangeIp = wareChangeIp;
	}
	public String getWareVersion() {
		return wareVersion;
	}
	public void setWareVersion(String wareVersion) {
		this.wareVersion = wareVersion;
	}
	public String getWareType() {
		return wareType;
	}
	public void setWareType(String wareType) {
		this.wareType = wareType;
	}
	
}
