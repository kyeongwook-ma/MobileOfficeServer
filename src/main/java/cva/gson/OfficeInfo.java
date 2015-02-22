package cva.gson;

import java.util.List;

public class OfficeInfo {
	
	private List<OfficeBlock> block;	
	private int office_cost;

	public OfficeInfo(List<OfficeBlock> block, int office_cost){
		this.block = block;
		this.office_cost = office_cost;
	}
	
	public List<OfficeBlock> getBlock() {
		return block;
	}

	public void setBlock(List<OfficeBlock> block) {
		this.block = block;
	}

	public int getOffice_cost() {
		return office_cost;
	}

	public void setOffice_cost(int office_cost) {
		this.office_cost = office_cost;
	}
}
