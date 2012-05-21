package com.xylon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseLog {
	protected static final Log log = LogFactory.getLog(BaseLog.class);
	
	protected static void debug(String text, Object[]  args){
		StringBuilder builder = new StringBuilder();
		builder.append(text).append(" : ");
		builder.append("[");
		for(int i = 0; i < args.length; i++){
			if(i != 0){
				builder.append(", ");
			}
			builder.append(args[i]);
		}
		builder.append("]");
		log.debug(builder.toString());
	}
}
