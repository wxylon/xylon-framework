package pku.test.service;


import org.drools.runtime.rule.QueryResults;

import pku.test.domain.comp.CompEvent;
import pku.test.domain.simple.SimpleEvent;

public interface IRuleService {
	/***
	 * receive the simple event then return the complicated event
	 * @param event
	 * @return
	 */
	public CompEvent receiveEvent(SimpleEvent event,String query);
	/**
	 * get query results
	 * @param query
	 * @return
	 */
	public QueryResults getQueryResults(String query);
}
