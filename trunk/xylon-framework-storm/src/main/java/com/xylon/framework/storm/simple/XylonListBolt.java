/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.storm.simple;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

/**
 * @author wxylon@gmail.com
 * @date 2013-2-19
 */
public class XylonListBolt extends BaseBasicBolt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6467077126541265761L;
	
	private static Log log = LogFactory.getLog(XylonListBolt.class.getName());
	
	private OutputCollector collector;

	@Override
	public void cleanup() {

	}

	public void execute(Tuple tuple) {
		try {
			log.info("execute----->处理数据");
			Person p= (Person)tuple.getValueByField("person");
			
			log.info("处理数据  用户="+p.toString());
			
			collector.ack(tuple);
		}catch (Exception e) {
			collector.fail(tuple);
			log.error("处理数据出现异常", e);
		}
		
	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector=collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		
	}
}

