package com.jd.schedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jd.fms.fundcollection.domain.ErpBillWS;
import com.jd.fms.fundcollection.service.ws.WSErpBillService;

public class QuartzMain {

    private final static Log log = LogFactory.getLog(QuartzMain.class);

   public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] { "spring-config-ws-client.xml" });
   public static WSErpBillService skuCostWorker =  (WSErpBillService)applicationContext.getBean("wsErpBillService");
    
    public static void main(String[] args) {
    	testRevokeOne();
    	testInsertOne();
    }
  
    public static void testInsert(){
//      ErpBill erpBill = new ErpBill();
//      erpBill.setCityName("beijing");
//      skuCostWorker.insertErpBill(erpBill);
      ErpBillWS erpBill1 = null;
      List<ErpBillWS> erpBills = new ArrayList<ErpBillWS>();
      for(int i = 0;  i < 100; i++){
      	erpBill1 = new ErpBillWS();
      	erpBill1.setCityName("tianjin" + i+100);
//      	if( i == 50){
//    		erpBill1.setUuid("ssssssssssssssssssssssssssssssssssssssss");
//    	}else{
    		erpBill1.setUuid(i+"100");
//    	}
      	erpBill1.setDepositAmout(BigDecimal.valueOf(10000));
      	erpBill1.setDepositTime("2011-01-01 23:23:23");
      	erpBill1.setFlag("1");
      	erpBills.add(erpBill1);
      }
     System.out.println(skuCostWorker.insertOrUpdateErpBills(erpBills));
    }
    
    public static void testupdate(){
    	ErpBillWS erpBill1 = null;
        List<ErpBillWS> erpBills = new ArrayList<ErpBillWS>();
        for(int i = 0;  i < 100; i++){
        	erpBill1 = new ErpBillWS();
        	erpBill1.setCityName("beijing" + i+100);
//        	if( i == 50){
//        		erpBill1.setUuid("10000000000000000000000000000000");
//        	}else{
        		erpBill1.setUuid(i+"100");
//        	}
        	erpBill1.setDepositAmout(BigDecimal.valueOf(10000));
        	erpBill1.setFlag("2");
        	erpBill1.setDepositTime("2011-01-01 23:23:23");
        	erpBills.add(erpBill1);
        }
       System.out.println(skuCostWorker.insertOrUpdateErpBills(erpBills));
    }
    
    public static void testRevoke(){
    	StringBuilder uuids = new StringBuilder();
    	for(int i = 0;  i < 100; i++){
    		if(i != 0){
    			uuids.append(", ");
    		}
    		uuids.append(i+"100");
        }
	 System.out.println(skuCostWorker.revokeErpBills(uuids.toString()));
    }
    
    public static void testInsertOne(){
    	ErpBillWS erpBill1 = new ErpBillWS();
    	erpBill1.setOrgCode(6);
    	erpBill1.setOrgName("北京分公司");
    	erpBill1.setCityCode(2806);
      	erpBill1.setCityName("石景山区");
      	erpBill1.setStationCode(1355);
      	erpBill1.setStationName("财务石景山站");
      	erpBill1.setStationType(4);
      	erpBill1.setStationTypeName("京东配送");
      	erpBill1.setDepositAmout(BigDecimal.valueOf(331092.00));
      	erpBill1.setDepositTime("2012-05-08");
      	erpBill1.setFlag("2");
      	erpBill1.setUuid("3VhUwq3YgDfs0HYN2m31rFY@");
      	System.out.println(skuCostWorker.insertOrUpdateErpBill(erpBill1));
//        (id, org_code, org_name, city_code, city_name, station_code, station_name,       
//      	station_type, station_type_name, deposit_amout, deposit_time, uuid)   values    
//      	(null, ?, ?, ?, ?, ?, ?,     ?, ?, ?, str_to_date(?, '%Y-%m-%d %H:%i:%s'), ?)  
//        [6, 北京分公司, 2806, 石景山区, 1355, 财务石景山站, 
//      	4, 京东配送, 331092.00, 2012-05-08, ]
      }
    
    public static void testRevokeOne(){
    	System.out.println(skuCostWorker.revokeErpBills("3VhUwq3YgDfs0HYN2m31rFY@"));
    }
}
