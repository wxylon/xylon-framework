package com.jd.whs.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.jd.whs.WareVO;

@WebService
public interface WhsService 
{	
	/** 1
	 *  获取�?��仓库
	 */
	@WebMethod(operationName="getWare")
	@WebResult(name="resultList")
	public List<WareVO> getWare();
	
	/** 2
	 *  根据仓库使用标志获取仓库的详细信�?
	 */
	@WebMethod(operationName="getWareFlag")
	@WebResult(name="resultList")
	public List<WareVO> getWareFlag(@WebParam(name="delFlag")int flag);
	
	
	/** 3
	 *  根据机构号获取该机构的所有配送中心�?仓库
	 */
	@WebMethod(operationName="getWareMcust")
	@WebResult(name="resultList")
	public List<WareVO> getWareMcust(@WebParam(name="organizationCode") String mcustno);
	
	
	/** 4
	 *  根据机构号�?使用标志获取�?��配�?中心、仓�?
	 */
	@WebMethod(operationName="getWareMcustFlag")
	@WebResult(name="resultList")
	public List<WareVO> getWareMcustFlag(@WebParam(name="organizationCode")String mcustno,
										 @WebParam(name="wareHouseDelFlag")int flag);
	
	
	
	/** 5
	 *  根据机构号�?配�?中心获取�?��配�?中心、仓�?
	 */
	@WebMethod(operationName="getWareMcustDeliver")
	@WebResult(name="resultList")
	public List<WareVO> getWareMcustDeliver(@WebParam(name="organizationCode") String mcustno, 
											@WebParam(name="deliveryCode")String deliverCode);
	
	
	/** 6
	 *  根据机构号�?配�?中心、使用标志获取所有配送中心�?仓库
	 */
	@WebMethod(operationName="getWareMcustDeliverFlag")
	@WebResult(name="resultList")
	public List<WareVO> getWareMcustDeliverFlag(@WebParam(name="organizationCode")String mcustno, 
												@WebParam(name="deliveryCode")String deliverCode,
												@WebParam(name="wareHouseDelFlag")int flag);
	
	
	/** 7
	 *  根据机构号�?配�?中心、ERP库房号获取所有配送中心�?仓库 
	 */
	@WebMethod(operationName="getWareMcustDeliverStore")
	@WebResult(name="resultList")
	public List<WareVO> getWareMcustDeliverStore(@WebParam(name="organizationCode")String mcustno, 
												 @WebParam(name="deliveryCode")String deliverCode, 
												 @WebParam(name="wareErpCode")String stroeid);
	
	
	/** 8
	 *  获取�?��不重复机�?
	 */
	@WebMethod(operationName="getMcustDistinct")
	@WebResult(name="resultList")
	public List<WareVO> getMcustDistinct();
	
	
	
	/** 9
	 *  根据配�?中心号和仓库获取库房
	 *  (配�?中心代码和ERP库房代码)
	 */
	@WebMethod(operationName="getWareDeliverStore")
	@WebResult(name="resultList")
	public List<WareVO> getWareDeliverStore(@WebParam(name="deliveryCode")int deliverCode, 
											@WebParam(name="wareErpCode")int stroeid);
	
	
	/** 10
	 *  根据配�?中心号获取所有库�?
	 */
	@WebMethod(operationName="getWareDeliver")
	@WebResult(name="resultList")
	public List<WareVO> getWareDeliver(@WebParam(name="deliveryCode")int deliverCode);
	
	
	
	/** 11
	 *  根据配�?中心号和使用标志获取库房
	 */
	@WebMethod(operationName="getWareDeliverFlag")
	@WebResult(name="resultList")
	public List<WareVO> getWareDeliverFlag(@WebParam(name="deliveryCode")int deliverCode,
										   @WebParam(name="wareHouseDelFlag")int flag);
	
	
	
	/** 12
	 *  获取不重复的配�?中心
	 */
	@WebMethod(operationName="getWareDeliverDistinct")
	@WebResult(name="resultList")
	public List<WareVO> getWareDeliverDistinct();
	
	
	/** 13
	 *  根据机构号获取不重复的配送中�?
	 */
	@WebMethod(operationName="getWareDeliverDistinctByMcust")
	@WebResult(name="resultList")
	public List<WareVO> getWareDeliverDistinctByMcust(@WebParam(name="organizationCode")int mcustno);
	
	
	/** 14
	 *  根据配�?中心号和仓库获取库房 (包括�?��应商信息)
	 */
	@WebMethod(operationName="getWareDeliverStoreVR")
	@WebResult(name="resultList")
	public List<WareVO> getWareDeliverStoreVR(@WebParam(name="deliveryCode")int deliverCode, 
											  @WebParam(name="wareErpCode")int stroeid);
}
