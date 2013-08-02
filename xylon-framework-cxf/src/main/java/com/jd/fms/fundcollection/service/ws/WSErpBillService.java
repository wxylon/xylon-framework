package com.jd.fms.fundcollection.service.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.jd.fms.fundcollection.domain.ErpBillWS;

@WebService(targetNamespace = "http://www.360buy.com/")
public interface WSErpBillService {
	/**
	 * 根据<b>erpBill.flag</b>标识,新增或者更新一条实收记录.<br/>
	 * erpBill.flag == 1,新增一条实收记录<br/>
	 * erpBill.flag == 2,更新一条实收记录; 更新一条记录,必须是已经撤销的<br/>
	 * @param erpBill	更新或者新增的实收记录
	 * @return			true/false; 只有更新出现异常的情况返回false;
	 * @author wangx
	 * @date 2012-5-10
	 */
	@WebResult(name="flag")
	public boolean insertOrUpdateErpBill(@WebParam(name="erpBill") ErpBillWS erpBill);
	
	/**
	 * 根据<b>erpBill.flag</b>标识,新增或者更新一批实收记录.<br/>
	 * erpBill.flag == 1,新增一批实收记录<br/>
	 * erpBill.flag == 2,更新一批实收记录; 更新一批记录,必须是已经撤销的<br/>
	 * @param erpBills	更新或者新增的实收记录集合,该集合中可以同事包含新增和更新记录。
	 * @return			true/false; 只有更新出现异常的情况返回false;
	 * @author wangx
	 * @date 2012-5-10
	 */
	@WebResult(name="flag")
	public boolean insertOrUpdateErpBills(@WebParam(name="erpBills") List<ErpBillWS> erpBills);
	
	/**
	 * 撤销已发布的实收记录.<br/>
	 * eg.uuids: <b>1, 2, 3, 4</b>
	 * @param uuids		uuid组成的字符串
	 * @return			true/false
	 * @author wangx
	 * @date 2012-5-10
	 */
	@WebResult(name="flag")
	public boolean revokeErpBills(@WebParam(name="Uuids") String uuids);
}
