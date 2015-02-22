package cva.roomConfig;

import java.io.BufferedReader;
import java.io.FileReader;

import cva.deviceConfig.Aircon;
import cva.deviceConfig.BlindController;
import cva.deviceConfig.Fan;
import cva.deviceConfig.Humidifier;
import cva.deviceConfig.Light;
import cva.deviceConfig.Stand;

public class Roominit {

	int row, col;
	protected static double[][] tempMass;
	protected static double[][] brightMass;
	protected static double[][] humiMass;
	protected boolean[][] has_window;
	protected boolean[][] TL;
	protected boolean[][] SL;
	protected boolean[][] A;
	protected boolean[][] F;
	protected boolean[][] BC;
	protected boolean[][] H;
	protected boolean[][] occupied;
	protected Aircon[][] aircon;
	protected BlindController[][] blindcontroller;
	protected Fan[][] fan;
	protected Humidifier[][] humidifier;
	protected Light[][] light;
	protected Stand[][] stand;

	Roominit(int row, int col) {
		this.row = row;
		this.col = col;

		tempMass = new double[row][col];
		brightMass = new double[row][col];
		humiMass = new double[row][col];
		has_window = new boolean[row][col];
		TL = new boolean[row][col];
		SL = new boolean[row][col];
		A = new boolean[row][col];
		F = new boolean[row][col];
		BC = new boolean[row][col];
		H = new boolean[row][col];
		occupied = new boolean[row][col];
		aircon = new Aircon[row][col];
		blindcontroller = new BlindController[row][col];
		fan = new Fan[row][col];
		humidifier = new Humidifier[row][col];
		light = new Light[row][col];
		stand = new Stand[row][col];
	}

	// .txt 파일을 읽어서 각 배열에 값을 할당
	public void roomspec(int state) {
		BufferedReader in = null;

		try {
			if (state == 1) {
				in = new BufferedReader(new FileReader("./Data/roomspec.txt"));
			}
			if (state == 2) {
				in = new BufferedReader(new FileReader("./Data/roomspec2.txt"));
			}
			if (state == 3) {
				in = new BufferedReader(new FileReader("./Data/roomspec3.txt"));
			}
			if (state == 4) {
				in = new BufferedReader(new FileReader("./Data/roomspec4.txt"));
			}
			if (state == 5) {
				in = new BufferedReader(new FileReader("./Data/roomspec5.txt"));
			}
			if (state == 6) {
				in = new BufferedReader(new FileReader("./Data/roomspec6.txt"));
			}

			String s;
			while ((s = in.readLine()) != null) {
				String[] cellSpec;
				cellSpec = s.split("\t");

				int x = Integer.parseInt(cellSpec[0]);
				int y = Integer.parseInt(cellSpec[1]);
				tempMass[x][y] = Double.parseDouble(cellSpec[2]);
				brightMass[x][y] = Double.parseDouble(cellSpec[3]);
				humiMass[x][y] = Double.parseDouble(cellSpec[4]);
				has_window[x][y] = checkBool(cellSpec[5]);
				TL[x][y] = checkBool(cellSpec[6]);
				SL[x][y] = checkBool(cellSpec[7]);
				A[x][y] = checkBool(cellSpec[8]);
				F[x][y] = checkBool(cellSpec[9]);
				BC[x][y] = checkBool(cellSpec[10]);
				H[x][y] = checkBool(cellSpec[11]);
				occupied[x][y] = checkBool(cellSpec[12]);
			}

			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// .txt 파일에 T로 입력된 문장을 true로 반환
	// F일 경우 False 반환
	public boolean checkBool(String data) {
		if (data.equals("T")) {
			return true;
		} else
			return false;
	}

	// room에서의 actuators 객체 생성
	/*
	 * public void roomsetting() { for (int i = 0; i < row; i++) { for (int j =
	 * 0; j < col; j++) { if (TL[i][j] == true) { light[i][j] = new Light(row,
	 * col, i, j); } if (SL[i][j] == true) { stand[i][j] = new Stand(row, col,
	 * i, j); } if (A[i][j] == true) { aircon[i][j] = new Aircon(row, col, i,
	 * j); } if (F[i][j] == true) { fan[i][j] = new Fan(row, col, i, j); } if
	 * (BC[i][j] == true) { blindcontroller[i][j] = new BlindController(row,
	 * col, i, j); } if (H[i][j] == true) { humidifier[i][j] = new
	 * Humidifier(row, col, i, j); } } } }
	 */

	// 변수에 대한 getter
	public double[][] getTempMass() {
		return tempMass;
	}

	public double[][] getBrightMass() {
		return brightMass;
	}

	public double[][] getHumiMass() {
		return humiMass;
	}

	public boolean[][] getHas_window() {
		return has_window;
	}

	public boolean[][] getTL() {
		return TL;
	}

	public boolean[][] getSL() {
		return SL;
	}

	public boolean[][] getA() {
		return A;
	}

	public boolean[][] getF() {
		return F;
	}

	public boolean[][] getBC() {
		return BC;
	}

	public boolean[][] getH() {
		return H;
	}

	public boolean[][] getOccupied() {
		return occupied;
	}

	public Aircon[][] getAircon() {
		return aircon;
	}

	public BlindController[][] getBlindcontroller() {
		return blindcontroller;
	}

	public Fan[][] getFan() {
		return fan;
	}

	public Light[][] getLight() {
		return light;
	}

	public Humidifier[][] getHumidifier() {
		return humidifier;
	}

	public Stand[][] getStand() {
		return stand;
	}
}
