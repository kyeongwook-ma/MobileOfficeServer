package cva.roomConfig;

public class RoomOccupied {

	double[][] temp;
	double[][] bright;
	double[][] humi;
	int[][] tempLogical;
	int[][] brightLogical;
	int[][] humiLogical;
	double[][] tempWish;
	double[][] brightWish;
	double[][] humiWish;
	String[][] name;

	public RoomOccupied(int row, int col){
		temp = new double[row][col];
		bright = new double[row][col];
		humi = new double[row][col];
		tempLogical = new int[row][col];
		brightLogical = new int[row][col];
		humiLogical = new int[row][col];
		tempWish = new double[row][col];
		brightWish = new double[row][col];
		humiWish = new double[row][col];
		name = new String[row][col];
		
		init();
	}
	
	
	public void init(){
		tempLogical[0][2] = 4;
		tempWish[0][2] = 26;
		name[0][2] ="Hong Kil Dong";
		tempLogical[2][2] = 5;
		tempWish[2][2] = 25;
		brightLogical[2][2] = 3;
		brightWish[2][2] = 60;
		name[2][2] = "Lee Tak Jin";
	}
	

	public String getName(int i, int j) {
		return name[i][j];
	}


	public void setName(int i, int j, String name) {
		this.name[i][j] = name;
	}
	
	public double getTempWish(int i, int j) {
		return tempWish[i][j];
	}


	public void setTempWish(double[][] tempWish) {
		this.tempWish = tempWish;
	}


	public double getBrightWish(int i, int j) {
		return brightWish[i][j];
	}


	public void setBrightWish(double[][] brightWish) {
		this.brightWish = brightWish;
	}


	public double getHumiWish(int i, int j) {
		return humiWish[i][j];
	}


	public void setHumiWish(double[][] humiWish) {
		this.humiWish = humiWish;
	}
	
	public int getTempLogical(int i, int j) {
		return tempLogical[i][j];
	}


	public void setTempLogical(int[][] tempLogical) {
		this.tempLogical = tempLogical;
	}


	public int getBrightLogical(int i, int j) {
		return brightLogical[i][j];
	}


	public void setBrightLogical(int[][] brightLogical) {
		this.brightLogical = brightLogical;
	}


	public int getHumiLogical(int i, int j) {
		return humiLogical[i][j];
	}


	public void setHumiLogical(int[][] humiLogical) {
		this.humiLogical = humiLogical;
	}


	public double[][] getTemp() {
		return temp;
	}

	public void setTemp(double[][] temp) {
		this.temp = temp;
	}

	public double[][] getBright() {
		return bright;
	}

	public void setBright(double[][] bright) {
		this.bright = bright;
	}

	public double[][] getHumi() {
		return humi;
	}

	public void setHumi(double[][] humi) {
		this.humi = humi;
	}

	public double getTemp(int i, int j) {
		return temp[i][j];
	}

	public double getBright(int i, int j) {
		return bright[i][j];
	}

	public double getHumi(int i, int j) {
		return humi[i][j];
	}
}
