/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.storm.simple;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
/**
 * @author wxylon@gmail.com
 * @date 2013-2-19
 */
public class XylonSpout extends BaseRichSpout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3980242432526476937L;
	private static Log log = LogFactory.getLog(XylonSpout.class.getName());
	Queue<Person> queues = new LinkedList<Person>();
	List<Person> list=null;
	
	private SpoutOutputCollector collector;
	private Map conf;
	private TopologyContext context;


	@Override
	public void ack(Object arg0) {
		log.info("ack-----> "+arg0);
	}

	@Override
	public void close() {
		log.info("close-----> ");
	}

	@Override
	public void fail(Object arg0) {
		log.info("fail-----> "+arg0);
	}

	@Override
	public void nextTuple() {
		Person p= queues.poll();
		if(p!=null){
			log.info("nextTuple----> 发送数据，用户ID="+p.getId());
			collector.emit(new Values(p), p);
		}
		
	}

	@Override
	public void open(Map map, TopologyContext context, SpoutOutputCollector collector) {
		log.info("spout-----> 将集合数据转换至队列中");
		this.collector = collector;
		this.conf = conf;
		this.context = context;
		
		Iterator<Person> it= list.iterator();
		while(it.hasNext()){
			Person p=it.next();
			queues.add(p);
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		log.info("declareOutputFields----> 设置输出字段");
		declarer.declare(new Fields("person"));
	}

	public XylonSpout(List<Person> list) {
		super();
		this.list = list;
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		log.info("getComponentConfiguration-----> ");
		return null;
	}
}

