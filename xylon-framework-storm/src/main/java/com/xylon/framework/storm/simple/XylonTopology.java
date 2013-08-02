/**
 * Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.framework.storm.simple;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

/**
 * @author wxylon@gmail.com
 * @date 2013-2-19
 */
public class XylonTopology{
	private static Log log = LogFactory.getLog(XylonTopology.class.getName());

	public static StormTopology buildTopology(List<Person> list) {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("1", new XylonSpout(list));
		builder.setBolt("2", new XylonListBolt()).globalGrouping("1");

		return builder.createTopology();
	}

	public static List<Person> getPerson() {
		List<Person> list = new ArrayList<Person>();

		for (int i = 1; i < 10; i++) {
			Person p = new Person();
			p.setId((long) i);
			p.setName("zhang" + i);
			p.setAge(20 + i);

			list.add(p);
		}
		return list;
	}

	public static List<String> getStr() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i < 10; i++) {
			list.add("test" + i);
		}
		return list;
	}

	public static void testTopology() {
		log.info("stormdemo开始");
		long startTime = System.currentTimeMillis();
		Config conf = new Config();
		conf.setDebug(true);
		//conf.setNumWorkers(2);
		//conf.setMaxSpoutPending(1);
		LocalCluster cluster = new LocalCluster();
		List<Person> list = getPerson();

		cluster.submitTopology("demo1", conf, buildTopology(list));
		long executeTime = System.currentTimeMillis();
		Utils.sleep(30000);
		log.info("stormdemo结束");
		long stopTime = System.currentTimeMillis();

		log.info("共消耗时间：运行=" + (executeTime - startTime) + ",总时间：" + (stopTime - startTime) + "");

		cluster.killTopology("demo1");
		cluster.shutdown();

	}
	
	public static void main(String[] args) {
		testTopology();
	}
}
