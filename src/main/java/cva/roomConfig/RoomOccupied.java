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
	String[][][] name;	
	int[][][] start_time;
	int[][][] end_time;

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
		name = new String[row][col][24];
		start_time = new int[row][col][24];
		end_time = new int[row][col][24];
		
		init();
	}
	
	
	public void init(){
		tempLogical[0][2] = 4;
		tempWish[0][2] = 26;
		start_time[0][2][15] = 15;
		end_time[0][2][15] = 17;
		name[0][2][15] ="Hong Kil Dong";
		start_time[0][2][16] = 15;
		end_time[0][2][16] = 17;
		name[0][2][16] ="Hong Kil Dong";
		start_time[0][2][17] = 15;
		end_time[0][2][17] = 17;		
		name[0][2][17] ="Hong Kil Dong";
		tempLogical[2][2] = 5;
		tempWish[2][2] = 25;
		brightLogical[2][2] = 3;
		brightWish[2][2] = 60;
		start_time[2][2][13] = 13;
		end_time[2][2][13] = 15;
		name[2][2][13] = "Lee Tak Jin";
		start_time[2][2][14] = 13;
		end_time[2][2][14] = 15;
		name[2][2][14] = "Lee Tak Jin";
		start_time[2][2][15] = 13;
		end_time[2][2][15] = 15;
		name[2][2][15] = "Lee Tak Jin";
	}
	
	public int getStart_time(int i, int j, int time) {
		return start_time[i][j][time];
	}


	public void setStart_time(int i, int j, int time, int start_time) {
		this.start_time[i][j][time] = start_time;
	}


	public int getEnd_time(int i, int j, int time) {
		return end_time[i][j][time];
	}


	public void setEnd_time(int i, int j, int time, int end_time) {
		this.end_time[i][j][time] = end_time;
	}


	public String getName(int i, int j, int time) {
		return name[i][j][time];
	}


	public void setName(int i, int j, int time, String name) {
		this.name[i][j][time] = name;
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
