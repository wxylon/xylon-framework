package pku.test.service;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import pku.test.domain.comp.CompEvent;
import pku.test.domain.simple.SimpleEvent;

public class RuleServiceByDrools implements IRuleService {

	private StatefulKnowledgeSession session;

	@Override
	public CompEvent receiveEvent(SimpleEvent event, String query) {
		System.out.println("room" + event.getRoomId() + " " + event.toString());
		QueryResults result = session.getQueryResults(query, event.getRoomId());
		for (QueryResultsRow pe : result) {
			session.retract(pe.getFactHandle("simpleEvent"));
		}
		session.insert(event);
		session.fireAllRules();
		session.dispose();
		return null;
	}

	@Override
	public QueryResults getQueryResults(String query) {
		return session.getQueryResults(query);
	}

	public StatefulKnowledgeSession getSession() {
		return session;
	}

	public void setSession(StatefulKnowledgeSession session) {
		this.session = session;
	}
}
