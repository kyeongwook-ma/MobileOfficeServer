package cva.gson;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import cva.roomConfig.Room;
import cva.roomConfig.RoomOccupied;

public class Response {

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
	boolean[][] has_window;
	boolean[][] TL;
	boolean[][] SL;
	boolean[][] A;
	boolean[][] F;
	boolean[][] BC;
	boolean[][] H;
	boolean[][] occupied;
	String[][][] useTL;
	String[][][] useSL;
	String[][][] useA;
	String[][][] useF;
	String[][][] useBC;
	String[][][] useH;
	Room r;
	RoomOccupied ro;
	int row, col;
	int cost;
	int[][][] start_time;
	int[][][] end_time;

	public Response(Room r, RoomOccupied ro, int row, int col) {
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
		has_window = new boolean[row][col];
		TL = new boolean[row][col];
		SL = new boolean[row][col];
		A = new boolean[row][col];
		F = new boolean[row][col];
		BC = new boolean[row][col];
		H = new boolean[row][col];
		useTL = new String[row][col][24];
		useSL = new String[row][col][24];
		useA = new String[row][col][24];
		useF = new String[row][col][24];
		useBC = new String[row][col][24];
		useH = new String[row][col][24];
		occupied = new boolean[row][col];
		this.r = r;
		this.ro = ro;
		this.row = row;
		this.col = col;
		cost = r.getBestCost();
		start_time = new int[row][col][24];
		end_time = new int[row][col][24];

		init();
	}

	public void init() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				temp[i][j] = r.getTemperature(i, j);
				bright[i][j] = r.getBright(i, j);
				humi[i][j] = r.getHumidity(i, j);
				tempLogical[i][j] = ro.getTempLogical(i, j);
				brightLogical[i][j] = ro.getBrightLogical(i, j);
				humiLogical[i][j] = ro.getHumiLogical(i, j);
				tempWish[i][j] = ro.getTempWish(i, j);
				brightWish[i][j] = ro.getBrightWish(i, j);
				humiWish[i][j] = ro.getHumiWish(i, j);				
				has_window[i][j] = r.getHasWindow(i, j);				
				TL[i][j] = r.getTL(i, j);
				SL[i][j] = r.getSL(i, j);
				A[i][j] = r.getA(i, j);
				F[i][j] = r.getF(i, j);
				BC[i][j] = r.getBC(i, j);
				H[i][j] = r.getH(i, j);
				occupied[i][j] = r.getOccupied(i, j);
				for (int k = 0; k < 24; k++) {
					useTL[i][j][k] = r.getUseTL(i, j,k);
					useSL[i][j][k] = r.getUseSL(i, j,k);
					useA[i][j][k] = r.getUseA(i, j,k);
					useF[i][j][k] = r.getUseF(i, j,k);
					useBC[i][j][k] = r.getUseBC(i, j,k);
					useH[i][j][k] = r.getUseH(i, j,k);
					name[i][j][k] = ro.getName(i, j,k);
					start_time[i][j][k] = ro.getStart_time(i, j, k);
					end_time[i][j][k] = ro.getEnd_time(i, j, k);
				}
			}
		}
	}

	public String call(int time) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();

		List<OfficeActuator> OfficeActuator[][] = new ArrayList[row][col];
		List<OfficeActuatorUse> OfficeActuatorUse[][] = new ArrayList[row][col];
		List<OfficeActuators> OfficeActuators[][] = new ArrayList[row][col];
		List<OfficeBlock> OfficeInfo = new ArrayList<OfficeBlock>();
		List<OfficeInfo> MobileOffice = new ArrayList<OfficeInfo>();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				OfficeActuator[i][j] = new ArrayList<OfficeActuator>();
				OfficeActuatorUse[i][j] = new ArrayList<OfficeActuatorUse>();
				OfficeActuators[i][j] = new ArrayList<OfficeActuators>();

				if (A[i][j] == true) {
					OfficeActuator[i][j].add(new OfficeActuator("1"));
				}
				if (TL[i][j] == true) {
					OfficeActuator[i][j].add(new OfficeActuator("2"));
				}
				if (SL[i][j] == true) {
					OfficeActuator[i][j].add(new OfficeActuator("3"));
				}
				if (F[i][j] == true) {
					OfficeActuator[i][j].add(new OfficeActuator("4"));
				}
				if (BC[i][j] == true) {
					OfficeActuator[i][j].add(new OfficeActuator("5"));
				}
				if (H[i][j] == true) {
					OfficeActuator[i][j].add(new OfficeActuator("6"));
				}

				if (useA[i][j].equals("AirconOn")) {
					OfficeActuatorUse[i][j].add(new OfficeActuatorUse(
							"AirconOn"));
				} else if (useA[i][j].equals("AirconOff")) {
					OfficeActuatorUse[i][j].add(new OfficeActuatorUse(
							"AirconOff"));
				}

				if (useTL[i][j].equals("LightOn")) {
					OfficeActuatorUse[i][j]
							.add(new OfficeActuatorUse("LightOn"));
				} else if (useTL[i][j].equals("LightOff")) {
					OfficeActuatorUse[i][j].add(new OfficeActuatorUse(
							"LightOff"));
				}

				if (useSL[i][j].equals("StandOn")) {
					OfficeActuatorUse[i][j]
							.add(new OfficeActuatorUse("StandOn"));
				} else if (useSL[i][j].equals("StandOff")) {
					OfficeActuatorUse[i][j].add(new OfficeActuatorUse(
							"StandOff"));
				}

				if (useF[i][j].equals("FanOn")) {
					OfficeActuatorUse[i][j].add(new OfficeActuatorUse("FanOn"));
				} else if (useF[i][j].equals("FanOff")) {
					OfficeActuatorUse[i][j]
							.add(new OfficeActuatorUse("FanOff"));
				}

				if (useBC[i][j].equals("BlindOn")) {
					OfficeActuatorUse[i][j]
							.add(new OfficeActuatorUse("BlindOn"));
				} else if (useBC[i][j].equals("BlindOff")) {
					OfficeActuatorUse[i][j].add(new OfficeActuatorUse(
							"BlindOff"));
				}

				if (useH[i][j].equals("HumiOn")) {
					OfficeActuatorUse[i][j]
							.add(new OfficeActuatorUse("HumiOn"));
				} else if (useH[i][j].equals("HumiOff")) {
					OfficeActuatorUse[i][j]
							.add(new OfficeActuatorUse("HumiOff"));
				}

				OfficeActuators[i][j].add(new OfficeActuators(
						OfficeActuator[i][j], OfficeActuatorUse[i][j]));

				String location = i + "," + j;
				
				OfficeInfo.add(new OfficeBlock(location, Double
						.toString(temp[i][j]), Double.toString(bright[i][j]),
						Double.toString(humi[i][j]), Boolean
								.toString(has_window[i][j]), name[i][j][time],
						start_time[i][j][time]+1, end_time[i][j][time]+1,
						OfficeActuators[i][j]));
			}
		}

		MobileOffice.add(new OfficeInfo(OfficeInfo, cost));

		String result = gson.toJson(new MobileOffice(MobileOffice));

		System.out.println("result : " + result);

		return result;

	}
}
