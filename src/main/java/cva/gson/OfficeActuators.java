package cva.gson;

import java.util.List;

public class OfficeActuators {
	
	private List<OfficeActuator> actuator;	
	private List<OfficeActuatorUse> OfficeActuatorUse;	

	public OfficeActuators(List<OfficeActuator> actuator, List<OfficeActuatorUse> OfficeActuatorUse){
		this.actuator = actuator;
		this.OfficeActuatorUse = OfficeActuatorUse;
	}
	
	public List<OfficeActuator> getActuator() {
		return actuator;
	}

	public void setActuator(List<OfficeActuator> actuator) {
		this.actuator = actuator;
	}
	
	public List<OfficeActuatorUse> getOfficeActuatorUse() {
		return OfficeActuatorUse;
	}

	public void setOfficeActuatorUse(List<OfficeActuatorUse> officeActuatorUse) {
		OfficeActuatorUse = officeActuatorUse;
	}
}
