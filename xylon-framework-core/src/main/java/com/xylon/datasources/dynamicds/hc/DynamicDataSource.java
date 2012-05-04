package com.xylon.datasources.dynamicds.hc;

import java.sql.SQLException;

public class DynamicDataSource extends AbstractRoutingDataSource {

	protected Object determineCurrentLookupKey() {
		return IndustryContextHolder.getIndustryType();
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

}
