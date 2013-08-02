package com.jd.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jd.whs.WareVO;
import com.jd.whs.server.WhsService;

public class QuartzMainWareHouse {

	private final static Log log = LogFactory.getLog(QuartzMainWareHouse.class);

	public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] { "spring-config-ws-client-warehouse.xml" });
	public static WhsService whsService = (WhsService) applicationContext.getBean("whsService");
	public List<WareVO> wareVOs_vaild = new ArrayList<WareVO>();
	public List<WareVO> wareVOs_not_vaild = new ArrayList<WareVO>();
	
	public static void main(String[] args){
		t();
	}
	
	public static void t(){
		StringBuilder sb = new StringBuilder();
		List<WareVO> vailds = whsService.getWare();
		for(WareVO wareVO : vailds){
			sb.append("deliveryCode:" + wareVO.getDeliveryCode() + "; ");
			sb.append("wareErpCode:" + wareVO.getWareErpCode() + "; ");
			sb.append("delFlag:" + wareVO.getWareDelFlag() + "; ");
			sb.append("organizationName:" + wareVO.getOrganizationName() + "; ");
			sb.append("organizationCode:" + wareVO.getOrganizationCode() + "; ");
			List<WareVO> temps = whsService.getWareDeliverStore(wareVO.getDeliveryCode(), wareVO.getWareErpCode());
			if(null == temps || temps.isEmpty()){
				sb.append("temps is empty");
			}else{
				sb.append("temps.size = " + temps.size() + "; ");
				sb.append("temps.get(0).getWareDelFlag() = " + temps.get(0).getWareDelFlag() + "; ");
				sb.append("temps.get(0).organizationName:" + temps.get(0).getOrganizationName() + "; ");
				sb.append("temps.get(0).organizationCode:" + temps.get(0).getOrganizationCode() + "; ");
			}
			System.out.println(sb.toString());
			sb.delete(0, sb.length());
		}
	}
	
	
	@Before
	public void init(){
		List<WareVO> vaild = whsService.getWareFlag(0);
		wareVOs_vaild.addAll(vaild);
		List<WareVO> not_vaild = whsService.getWareFlag(1);
		wareVOs_not_vaild.addAll(not_vaild);
	}
	
	
	/** 1
	 *  获取所有仓库
	 */
	@Test
	public void getWare(){
		List<WareVO> wareVOs = whsService.getWare();
		int size = wareVOs.size();
		Assert.assertEquals(size, wareVOs_vaild.size());
		for(WareVO temp : wareVOs){
			boolean r = false;
			for(WareVO wareVO : wareVOs_vaild){
				if(temp.getWareId() == wareVO.getWareId()){
					r = true;
				}
			}
			Assert.assertTrue(r);
		}
		for(WareVO temp : wareVOs){
			boolean r = false;
			for(WareVO wareVO : wareVOs_not_vaild){
				if(temp.getWareId() == wareVO.getWareId()){
					r = true;
				}
			}
			Assert.assertTrue(!r);
		}
		
	}
	
	@Test
	public void getWareMcust(){
		List<WareVO> wareVOs1 = whsService.getWareMcustFlag("4", 1);
		wareVOs1 = wareVOs1 == null ? new ArrayList<WareVO>() : wareVOs1;
		List<WareVO> wareVOs2 = whsService.getWareMcustFlag("4", 0);
		List<WareVO> wareVOs3 = whsService.getWareMcust("4");
		Assert.assertEquals(wareVOs3.size(), wareVOs2.size());
		
		for(WareVO temp : wareVOs3){
			boolean r = false;
			for(WareVO wareVO : wareVOs2){
				if(temp.getWareId() == wareVO.getWareId()){
					r = true;
				}
			}
			Assert.assertTrue(r);
		}
		for(WareVO temp : wareVOs3){
			boolean r = false;
			for(WareVO wareVO : wareVOs1){
				if(temp.getWareId() == wareVO.getWareId()){
					r = true;
				}
			}
			Assert.assertTrue(!r);
		}
	}
	
	@Test
	public void getWareMcustDeliver(){
		List<WareVO> wareVOs1 = whsService.getWareMcustDeliverFlag("4", "4", 1);
		wareVOs1 = wareVOs1 == null ? new ArrayList<WareVO>() : wareVOs1;
		List<WareVO> wareVOs2 = whsService.getWareMcustDeliverFlag("4", "4", 0);
		List<WareVO> wareVOs3 = whsService.getWareMcustDeliver("4", "4");
		Assert.assertEquals(wareVOs3.size(), wareVOs2.size());
		
		for(WareVO temp : wareVOs3){
			boolean r = false;
			for(WareVO wareVO : wareVOs2){
				if(temp.getWareId() == wareVO.getWareId()){
					r = true;
				}
			}
			Assert.assertTrue(r);
		}
		for(WareVO temp : wareVOs3){
			boolean r = false;
			for(WareVO wareVO : wareVOs1){
				if(temp.getWareId() == wareVO.getWareId()){
					r = true;
				}
			}
			Assert.assertTrue(!r);
		}
		
		for(WareVO temp : wareVOs3){
			List<WareVO> t = whsService.getWareMcustDeliverStore(""+temp.getOrganizationCode(), ""+temp.getDeliveryCode(), ""+temp.getWareErpCode());
			Assert.assertEquals(t.size(), 1);
			Assert.assertEquals(t.get(0).getWareDelFlag(), 0);
		}
		
	}
	
	@Test
	public void getMcustDistinct(){
		List<WareVO> t = whsService.getMcustDistinct();
		for(WareVO temp : t){
			Assert.assertEquals(temp.getWareDelFlag(), 0);
		}
	}
	
	@Test
	public void getWareDeliver(){
		List<WareVO> k = whsService.getWareDeliverFlag(4, 0);
		List<WareVO> t = whsService.getWareDeliver(4);
		List<WareVO> a = new ArrayList<WareVO>(t.size());
		for(WareVO temp : t){
			a.addAll(whsService.getWareDeliverStore(temp.getDeliveryCode(), temp.getWareErpCode()));
		}
		Assert.assertTrue(t.size() == a.size());
		Assert.assertTrue(t.size() == k.size());
		
		
		List<WareVO> m = whsService.getWareDeliverFlag(4, 1);
		m = m == null ? new ArrayList<WareVO>() : m;
		for(WareVO temp : m){
			Assert.assertTrue(temp.getWareDelFlag() == 1);
		}
	}
	
	
	private String bean2Attributes(Object object){
		StringBuilder attributes = new StringBuilder();
		attributes.append("[");
		int i = 0;
		try {
			Map<String, Object> map = BeanUtils.describe(object);
			for(Map.Entry<String, Object> kv : map.entrySet()){
				if(i != 0){
					attributes.append(", ");
				}
				attributes.append(kv.getKey()).append(":").append(kv.getValue());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		attributes.append("]");
		return attributes.toString();
	}
	
	private String bean2AttributesList(List<WareVO> wareVOs, boolean format){
		StringBuilder attributes = new StringBuilder();
		attributes.append("{");
		if(format) attributes.append("\r\n");
		for(Object object : wareVOs){
			if(format) attributes.append("\t");
			attributes.append(bean2Attributes(object));
			if(format) attributes.append("\r\n");
		}
		attributes.append("}");
		return attributes.toString();
	}
	
	private String bean2AttributesList(List<WareVO> wareVOs){
		return bean2AttributesList(wareVOs, true);
	}
}
