package cva.gson;

import java.util.List;

public class OfficeBlock {

	private String location;
	private String temperature;
	private String brightness;
	private String humidity;
	private String has_window;
	private String name;
    private int start_time;
    private int end_time;

	private List<OfficeActuators> actuators;	

	public OfficeBlock(String location, String temperature, String brightness,
			String humidity, String has_window, String name, int start_time, int end_time, List<OfficeActuators> actuators) {
		
		this.location = location;
		this.temperature = temperature;
		this.brightness = brightness;
		this.humidity = humidity;
		this.has_window = has_window;
		this.actuators = actuators;
		this.name = name;
		this.start_time = start_time;
		this.end_time = end_time;
	}

    public int getStart_time() {
        return start_time;
    }
    
    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }    

    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getBrightness() {
		return brightness;
	}

	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getHas_window() {
		return has_window;
	}

	public void setHas_window(String has_window) {
		this.has_window = has_window;
	}

	public List<OfficeActuators> getActuators() {
		return actuators;
	}

	public void setActuators(List<OfficeActuators> actuators) {
		this.actuators = actuators;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
