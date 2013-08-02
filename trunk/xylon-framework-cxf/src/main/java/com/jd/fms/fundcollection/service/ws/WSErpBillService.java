package com.jd.fms.fundcollection.service.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.jd.fms.fundcollection.domain.ErpBillWS;

@WebService(targetNamespace = "http://www.360buy.com/")
public interface WSErpBillService {
	/**
	 * ����<b>erpBill.flag</b>��ʶ,�������߸���һ��ʵ�ռ�¼.<br/>
	 * erpBill.flag == 1,����һ��ʵ�ռ�¼<br/>
	 * erpBill.flag == 2,����һ��ʵ�ռ�¼; ����һ����¼,�������Ѿ�������<br/>
	 * @param erpBill	���»���������ʵ�ռ�¼
	 * @return			true/false; ֻ�и��³����쳣���������false;
	 * @author wangx
	 * @date 2012-5-10
	 */
	@WebResult(name="flag")
	public boolean insertOrUpdateErpBill(@WebParam(name="erpBill") ErpBillWS erpBill);
	
	/**
	 * ����<b>erpBill.flag</b>��ʶ,�������߸���һ��ʵ�ռ�¼.<br/>
	 * erpBill.flag == 1,����һ��ʵ�ռ�¼<br/>
	 * erpBill.flag == 2,����һ��ʵ�ռ�¼; ����һ����¼,�������Ѿ�������<br/>
	 * @param erpBills	���»���������ʵ�ռ�¼����,�ü����п���ͬ�°��������͸��¼�¼��
	 * @return			true/false; ֻ�и��³����쳣���������false;
	 * @author wangx
	 * @date 2012-5-10
	 */
	@WebResult(name="flag")
	public boolean insertOrUpdateErpBills(@WebParam(name="erpBills") List<ErpBillWS> erpBills);
	
	/**
	 * �����ѷ�����ʵ�ռ�¼.<br/>
	 * eg.uuids: <b>1, 2, 3, 4</b>
	 * @param uuids		uuid��ɵ��ַ���
	 * @return			true/false
	 * @author wangx
	 * @date 2012-5-10
	 */
	@WebResult(name="flag")
	public boolean revokeErpBills(@WebParam(name="Uuids") String uuids);
}
