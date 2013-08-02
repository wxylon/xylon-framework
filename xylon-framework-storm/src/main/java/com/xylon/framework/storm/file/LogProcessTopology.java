/**
 * Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.framework.storm.file;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

/**
 * http://bkeep.blog.163.com/blog/static/123414290201293043453325/
 * @author wxylon@gmail.com
 * @date 2013-2-20
 */
public class LogProcessTopology {
	
	private static Log log = LogFactory.getLog(LogProcessTopology.class);
	
	public static void main(String[] argv) throws AlreadyAliveException, InvalidTopologyException {
		String dataFile = argv[0]; // 输入文件
		String seperator = argv[1]; // 分隔符
		String outFile = argv[2]; // 输出文件
		
		log.info("dataFile: " + dataFile + "; seperator:" + seperator + "; outFile:" + outFile + ";");
		
		TopologyBuilder builder = new TopologyBuilder(); // build一个topology
		builder.setSpout("1", new FileSpout(dataFile)); // 指定spout
		builder.setBolt("2", new ProcessBolt(seperator, outFile)).shuffleGrouping("1"); // 指定bolt，包括bolt、process和grouping
		
//		builder.setSpout("spout", new FileSpout(dataFile), 1);   //指定spout
//      builder.setBolt("bolt", new ProcessBolt(seperator,outFile),1).shuffleGrouping("spout");  //指定bolt，包括bolt、process和grouping
		
		Config conf = new Config();
		conf.setDebug(true);
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("logProcessTopology", conf, builder.createTopology());
		
		Utils.sleep(30000);
		cluster.killTopology("logProcessTopology");
		cluster.shutdown();
	}
}
