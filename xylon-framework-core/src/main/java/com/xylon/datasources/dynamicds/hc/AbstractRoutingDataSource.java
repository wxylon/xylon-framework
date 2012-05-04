package com.xylon.datasources.dynamicds.hc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

public abstract class AbstractRoutingDataSource extends AbstractDataSource {
	private Map targetDataSources;

	private Object defaultTargetDataSource;

	public void setTargetDataSources(Map targetDataSources) {
		this.targetDataSources = targetDataSources;
	}

	public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
		this.defaultTargetDataSource = defaultTargetDataSource;
	}

	public Connection getConnection() throws SQLException {
		return determineTargetDataSource().getConnection();
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		return determineTargetDataSource().getConnection(username, password);
	}

	protected DataSource determineTargetDataSource() {
		Object lookupKey = determineCurrentLookupKey();
		DataSource dataSource = (DataSource) this.targetDataSources.get(lookupKey);
		if (dataSource == null) {
			dataSource = (DataSource) this.defaultTargetDataSource;
		}
		if (dataSource == null) {
			throw new IllegalStateException("Cannot determine target DataSource for lookup key ["+ lookupKey + "]");
		}
		return dataSource;
	}

	protected abstract Object determineCurrentLookupKey();
}
