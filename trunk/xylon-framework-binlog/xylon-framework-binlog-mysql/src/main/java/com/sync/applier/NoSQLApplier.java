package com.sync.applier;

import java.io.StringReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.update.Update;

import com.sync.dbms.DBMSData;
import com.sync.dbms.StatementData;
import com.sync.event.DBMSEvent;

/**
 * 支持 NOSQL 数据持久化 
 *  
 * @author ben
 *
 */

public abstract class NoSQLApplier implements Applier {
	
	static Logger logger = Logger.getLogger(NoSQLApplier.class);
	
	private CCJSqlParserManager spm = new CCJSqlParserManager();
	
	@Override
	public void apply(DBMSEvent event, boolean doCommit) {
		ArrayList<DBMSData> data = event.getData();
		for (DBMSData dataElem : data) {
			if (dataElem instanceof StatementData) {
				applyStatementData((StatementData) dataElem);
			}
		}
	}
	
	private void applyStatementData(StatementData data) {
		String sql = null;
		if (data.getQuery() != null) {
			sql = data.getQuery();
		} else {
			sql = new String(data.getQueryAsBytes());
		}
		
		try {
			net.sf.jsqlparser.statement.Statement statement = spm.parse(new StringReader(sql));
			
			 if (statement instanceof Insert) {			
				 Insert insert = ((Insert) statement);
				 insertStatement(insert);
				 
			 }else if (statement instanceof Update) {
				 Update update = ((Update)statement);
				 updateStatement(update);
				 
			 }else if (statement instanceof Delete) {
				 Delete delete = ((Delete) statement);
				 deleteStatement(delete);
				 
			 }else if (statement instanceof Replace) {
				 Replace replace = ((Replace)statement);
				 replaceStatement(replace);
			 }
			
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void insertStatement(Insert insert);
	
	protected abstract void updateStatement(Update update);
	
	protected abstract void deleteStatement(Delete delete);
	
	protected abstract void replaceStatement(Replace replace);

}
