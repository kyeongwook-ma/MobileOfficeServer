package cva.deviceConfig;

public abstract class Device {
	static final double DiscountRate = 0.05;
	static final double CoolerDryPerformance = 1.0;
	static final double CoolingPerformance = 3.0;
	static final double HeaterDryPerformance = 1.0;
	static final double HeatingPerformance = 3.0;
	static final double HumidifierPerformance = 2.0;
	static final double DehumidifierPerformance = 2.0;
	static final double AirCleaniingPerformance = 1.0;
	
	protected int maxrow, maxcol;
	protected int xPosition;
	protected int yPosition;
	
	Device(int maxrow, int maxcol, int xPos, int yPos) {
		this.maxrow = maxrow;
		this.maxcol = maxcol;
		this.xPosition = xPos;
		this.yPosition = yPos;		
	}
	
	protected int distance(int orgx, int orgy, int destx, int desty) {
		return java.lang.Math.max(java.lang.Math.abs(orgx - destx), java.lang.Math.abs(orgy - desty));
	}	
	
	abstract public int predictChanges(double[][] temperature, double[][] bright, double[][] humidity,
			double temp, double brig, double humi, int tempstate, int brightstate, int humistate);
	abstract public void on(double[][] temperature, double[][] bright, double[][] humidity, int elapseTime);
	abstract public void off(double[][] temperature, double[][] bright, double[][] humidity, int elapseTime);
	abstract public void dehumidify(double[][] temperature, double[][] bright, double[][] humidity, int elapseTime);
	abstract public void warm(double[][] temperature, double[][] bright, double[][] humidity, int elapseTime);
	abstract public void cool(double[][] temperature, double[][] bright, double[][] humidity, int elapseTime);
	abstract public int costW(int elapseTime);
}
