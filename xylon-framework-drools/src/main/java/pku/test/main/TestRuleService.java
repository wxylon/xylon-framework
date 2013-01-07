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
	 * 测试spring 管理 drools 两个事件都发生在房间1
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
	 * 测试spring 管理 drools 两个事件发生在不同的房间
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
	 * 测试互斥性 1. A1表示一个人进入到房间 A2表示一个人出了房间 B1表示温度的范围<20度 B2表示温度>=20度 2.
	 * 此时事件发生的顺序为A1，A2,B1
	 */
	// @Test
	public void testExEvent() {
		// A1事件
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// A2事件
		pEvent = new PeopleEvent();
		pEvent.setComing(false);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// B1事件
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
	}

	/**
	 * 测试互斥性 1. A1表示一个人进入到房间 A2表示一个人出了房间 B1表示温度的范围<20度 B2 表示温度>=20度
	 * 此时事件发生的顺序为B1,B2,A1
	 */
	//@Test
	public void testExEvent2() {
		// B1事件
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
		// B2事件
		tEvent = new TemperatureEvent();
		tEvent.setTemperature(23);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
		// A1事件
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
	}

	/**
	 * 检验不同房间传来的同类事件，不会产生冲突
	 * 
	 * 1. A1表示一个人进入到房间1 A2表示一个人出了房间2 B1表示房间1的温度范围<20度
	 * 
	 */
	@Test
	public void testExEvent3() {
		// A1事件
		PeopleEvent pEvent = new PeopleEvent();
		pEvent.setComing(true);
		pEvent.setRoomId(1);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// A2事件
		pEvent = new PeopleEvent();
		pEvent.setComing(false);
		pEvent.setRoomId(2);
		ruleService.receiveEvent(pEvent, PeopleEvent.QUERY);
		// B1事件
		TemperatureEvent tEvent = new TemperatureEvent();
		tEvent.setTemperature(13);
		tEvent.setRoomId(1);
		ruleService.receiveEvent(tEvent, TemperatureEvent.QUERY);
	}
}
