package cva.deviceConfig;

public class Light extends Device {	
	
	int costW;

	public Light(int maxrow, int maxcol, int xPos, int yPos) {
		super(maxrow, maxcol, xPos, yPos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int predictChanges(double[][] temperature, double[][] bright,
			double[][] humidity, double temp, double brig, double humi,
			int tempstate, int brightstate, int humistate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void on(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		// TODO Auto-generated method stub
		this.costW = 10;
		for(int i=0; i<this.maxrow; i++) {
			for(int j=0; j<this.maxcol; j++) {
				//humidity[i][j] += ((humiP - distance(xPosition, yPosition, i, j) * rate) * elapseTime);
				
				double distance = distance(xPosition, yPosition, i, j) * 0.1;
				double temprate = (0.15 - distance);	
				double brightrate = (0.3 - distance);
				
				if(temprate > 0){
					temperature[i][j] += temprate * elapseTime;
					bright[i][j] += brightrate * elapseTime;
				}			
			}
		}
	}

	@Override
	public void off(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		this.costW = 0;
		// TODO Auto-generated method stub
		for(int i=0; i<this.maxrow; i++) {
			for(int j=0; j<this.maxcol; j++) {
				//humidity[i][j] += ((humiP - distance(xPosition, yPosition, i, j) * rate) * elapseTime);
				
				double distance = distance(xPosition, yPosition, i, j) * 0.1;
				double brightrate = (0.3 - distance);				
				
				if(brightrate > 0){
					bright[i][j] -= brightrate * elapseTime;
				}			
			}
		}
	}

	@Override
	public void dehumidify(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warm(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cool(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int costW(int elapseTime) {
		// TODO Auto-generated method stub
		return elapseTime * costW;
	}
}
