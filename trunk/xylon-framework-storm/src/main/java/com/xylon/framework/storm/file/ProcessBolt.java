/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.storm.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
/**
 * @author wxylon@gmail.com
 * @date 2013-2-20
 */
public class ProcessBolt extends BaseRichBolt{
	private static Log log = LogFactory.getLog(FileSpout.class);
	private String _seperator;
	private String _outFile;
	PrintWriter pw;
	private OutputCollector _collector;
	private BufferedWriter bw;

	public ProcessBolt(String seperator, String outFile) {
		this._seperator = seperator;
		this._outFile = outFile;

	}

	// 把输出结果保存到外部文件里面。
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this._collector = collector;
		File out = new File(_outFile);
		try {
			// br = new BufferedWriter(new FileWriter(out));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out, true)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// blot计算单元，把tuple中的数据添加一个bkeep和回车。然后保存到outfile指定的文件中。
	@Override
	public void execute(Tuple input) {
		String line = input.getString(0);
		log.info("line: " + line);
		String[] str = line.split(_seperator);
		if(str.length < 3){
			return;
		}
		System.out.println(str[2]);
		log.info("str[2]: " + str[2]);
		try {
			bw.write(str[2] + ",bkeep" + "\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		_collector.emit(new Values(line));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
}

