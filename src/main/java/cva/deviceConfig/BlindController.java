package cva.deviceConfig;

public class BlindController extends Device {	
	int costW;
	
	public BlindController(int maxrow, int maxcol, int xPos, int yPos) {
		super(maxrow, maxcol, xPos, yPos);		
	}
	
	@Override
	public int predictChanges(double[][] temperature, double[][] bright,
			double[][] humidity, double temp, double brig, double humi,
			int tempstate, int brightstate, int humistate) {
		return 0;
	}

	@Override
	public void on(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		this.costW = 5;
		for(int i=0; i<this.maxrow; i++) {
			for(int j=0; j<this.maxcol; j++) {
				//humidity[i][j] += ((humiP - distance(xPosition, yPosition, i, j) * rate) * elapseTime);
				
				double distance = distance(xPosition, yPosition, i, j) * 0.1;
				double temprate = (0.2 - distance);				
				
				if(temprate > 0){
					temperature[i][j] += temprate * elapseTime;
				}			
			}
		}
	}

	@Override
	public void off(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		this.costW = 5;
		for(int i=0; i<this.maxrow; i++) {
			for(int j=0; j<this.maxcol; j++) {
				//humidity[i][j] += ((humiP - distance(xPosition, yPosition, i, j) * rate) * elapseTime);
				
				double distance = distance(xPosition, yPosition, i, j) * 0.1;			
				double tempbright = (0.3 - distance);				
				
				if(tempbright > 0){
					bright[i][j] -= tempbright * elapseTime;
				}
			}
		}
	}

	@Override
	public void dehumidify(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		
	}

	@Override
	public void warm(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		
	}

	@Override
	public void cool(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		
	}

	@Override
	public int costW(int elapseTime) {
		return elapseTime * costW;
	}
}
