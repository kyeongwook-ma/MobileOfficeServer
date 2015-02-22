package cva.deviceConfig;


public class Humidifier extends Device {
	
	int costW;
	
	public Humidifier(int maxrow, int maxcol, int xPos, int yPos) {
		super(maxrow, maxcol, xPos, yPos);
//		this.humiMass = new double[this.length][this.breadth];
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
		double distance = 0;
		double humirate = 0;
		this.costW = 6;
		for(int i=0; i<this.maxrow; i++) {
			for(int j=0; j<this.maxcol; j++) {
				//humidity[i][j] += ((humiP - distance(xPosition, yPosition, i, j) * rate) * elapseTime);
				
				distance = distance(xPosition, yPosition, i, j) * 0.1;
				humirate = (0.4 - distance);				
				
				if(humirate > 0){
					humidity[i][j] += humirate * elapseTime;
				}			
			}
		}
	}

	@Override
	public void off(double[][] temperature, double[][] bright,
			double[][] humidity, int elapseTime) {
		// TODO Auto-generated method stub
		
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
