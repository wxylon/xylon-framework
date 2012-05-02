package com.xylon.framework.dynamicds;

public class IndustryContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setIndustryType(String industryType) {
		contextHolder.set(industryType);
	}

	public static String getIndustryType() {
		return (String) contextHolder.get();
	}

	public static void clearIndustryType() {
		contextHolder.remove();
	}

}
