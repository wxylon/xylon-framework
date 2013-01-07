package pku.test.domain.simple;

public class PeopleEvent extends SimpleEvent {

	protected boolean isComing;
	public static final String QUERY = "people fact";
	public boolean getIsComing() {
		return isComing;
	}
	
	public void setComing(boolean isComing) {
		this.isComing = isComing;
	}
	
	@Override
	public String toString() {
		return "people iscoming: "+isComing;
	}
}
