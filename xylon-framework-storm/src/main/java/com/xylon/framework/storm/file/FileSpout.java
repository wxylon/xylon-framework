/**
 * Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.framework.storm.file;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

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
 * @date 2013-2-20
 */
public class FileSpout extends BaseRichSpout {
	private static Log log = LogFactory.getLog(FileSpout.class);
	/**
     */
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector _collector;
	private BufferedReader br;
	private String dataFile;

	// 定义spout文件
	FileSpout(String dataFile) {
		this.dataFile = dataFile;
	}

	// 定义如何读取spout文件
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		_collector = collector;
		File csv = new File(dataFile); // log file
		try {
			br = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 获取下一个tuple的方法
	@Override
	public void nextTuple() {
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				log.info("line: " + line);
				_collector.emit(new Values(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}

}
