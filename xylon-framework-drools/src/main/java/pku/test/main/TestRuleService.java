package pku.test.main;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pku.test.domain.simple.PeopleEvent;
import pku.test.domain.simple.TemperatureEvent;
import pku.test.service.IRuleService;

public class TestRuleService {

	private ApplicationContext context;
	private IRuleService ruleService;

	public TestRuleService() {
		context = new ClassPathXmlApplicationContext("beans.xml");
		ruleService = (IRuleService) context.getBean("ruleService");
	}

	/**
	 * ����spring ���� drools �����¼��������ڷ���1
	 */
	// @Test
	public void testEvent() {
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
	}

	/**
	 * ����spring ���� drools �����¼������ڲ�ͬ�ķ���
	 */
	// @Test
	public void testEvent2() {
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(2);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
	}

	/**
	 * ���Ի����� 1. A1��ʾһ���˽��뵽���� A2��ʾһ���˳��˷��� B1��ʾ�¶ȵķ�Χ<20�� B2��ʾ�¶�>=20�� 2.
	 * ��ʱ�¼�������˳��ΪA1��A2,B1
	 */
	// @Test
	public void testExEvent() {
		// A1�¼�
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// A2�¼�
		pEvent = new PeopleEvent();
		pEvent.setComing(false);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// B1�¼�
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
	}

	/**
	 * ���Ի����� 1. A1��ʾһ���˽��뵽���� A2��ʾһ���˳��˷��� B1��ʾ�¶ȵķ�Χ<20�� B2 ��ʾ�¶�>=20��
	 * ��ʱ�¼�������˳��ΪB1,B2,A1
	 */
	//@Test
	public void testExEvent2() {
		// B1�¼�
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
		// B2�¼�
		tEvent = new TemperatureEvent();
		tEvent.setTemperature(23);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
		// A1�¼�
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
	}

	/**
	 * ���鲻ͬ���䴫����ͬ���¼������������ͻ
	 * 
	 * 1. A1��ʾһ���˽��뵽����1 A2��ʾһ���˳��˷���2 B1��ʾ����1���¶ȷ�Χ<20��
	 * 
	 */
	@Test
	public void testExEvent3() {
		// A1�¼�
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// A2�¼�
		pEvent = new PeopleEvent();
		pEvent.setComing(false);
		pEvent.setRoomId(2);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// B1�¼�
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
	}
}
