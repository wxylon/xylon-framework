package pku.test.domain.simple;

public class TemperatureEvent extends SimpleEvent{

	protected float temperature;
	public static final String QUERY = "temperature fact";
	
	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public String toString() {
		return "The temperature:"+temperature;
	}
}
