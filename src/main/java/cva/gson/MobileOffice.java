package cva.gson;

import java.util.List;

public class MobileOffice {
	private List<OfficeInfo> mobile_office;
	
	public MobileOffice(List<OfficeInfo> mobile_office){
		this.mobile_office = mobile_office;
	}

	public List<OfficeInfo> getInfo() {
		return mobile_office;
	}

	public void setInfo(List<OfficeInfo> info) {
		this.mobile_office = info;
	}
}
