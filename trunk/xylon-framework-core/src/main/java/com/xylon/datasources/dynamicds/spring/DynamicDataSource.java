package com.xylon.datasources.dynamicds.spring;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * http://macrochen.iteye.com/blog/246148
 * http://fxiaozj.iteye.com/blog/1420833
 * @author Administrator
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

   protected Object determineCurrentLookupKey() {
      return CustomerContextHolder.getCustomerType();
   }

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
	
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
}
