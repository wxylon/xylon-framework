package com.xylon.datasources.dynamicds.hc;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class AbstractDataSource implements DataSource {

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public void setLoginTimeout(int timeout) throws SQLException {
		throw new UnsupportedOperationException("setLoginTimeout");
	}

	public PrintWriter getLogWriter() {
		throw new UnsupportedOperationException("getLogWriter");
	}

	public void setLogWriter(PrintWriter pw) throws SQLException {
		throw new UnsupportedOperationException("setLogWriter");
	}

}
