package com.xylon.datasources.dynamicds.spring;

import java.util.List;

import org.junit.Assert;

public class CatalogTests {

	   private Catalog catalog;

	   public void setCatalog(Catalog catalog) {
	      this.catalog = catalog;
	   }

	   public void testDataSourceRouting() {
	      CustomerContextHolder.setCustomerType(CustomerType.GOLD);
	      List<Item> goldItems = catalog.getItems();
	      Assert.assertEquals(3, goldItems.size());
	      System.out.println("gold items: " + goldItems);

	      CustomerContextHolder.setCustomerType(CustomerType.SILVER);
	      List<Item> silverItems = catalog.getItems();
	      Assert.assertEquals(2, silverItems.size());
	      System.out.println("silver items: " + silverItems);
	        
	      CustomerContextHolder.clearCustomerType();
	      List<Item> bronzeItems = catalog.getItems();
	      Assert.assertEquals(1, bronzeItems.size());
	      System.out.println("bronze items: " + bronzeItems);              
	   }

	   protected String[] getConfigLocations() {
	      return new String[] {"/blog/datasource/beans.xml"};
	   }    
	}
