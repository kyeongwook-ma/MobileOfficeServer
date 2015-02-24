package cva.roomConfig;

import java.util.ArrayList;

import cva.deviceConfig.Aircon;
import cva.deviceConfig.BlindController;
import cva.deviceConfig.Device;
import cva.deviceConfig.Fan;
import cva.deviceConfig.Humidifier;
import cva.deviceConfig.Light;
import cva.deviceConfig.Stand;
import cva.roomConfig.Roominit;
import cva.roomConfig.RoomEnvChecker;
import cva.roomConfig.RoomOccupied;

public class Room {
	protected int row, col;
	protected double[][] tempMass;
	protected double[][] brightMass;
	protected double[][] humiMass;
	protected boolean[][] has_window;
	protected boolean[][] TL;
	protected boolean[][] SL;
	protected boolean[][] A;
	protected boolean[][] F;
	protected boolean[][] BC;
	protected boolean[][] H;
	protected boolean[][] occupied;

	String[][][] useTL;
	String[][][] useSL;
	String[][][] useA;
	String[][][] useF;
	String[][][] useBC;
	String[][][] useH;

	protected Aircon[][] aircon;
	protected BlindController[][] blindcontroller;
	protected Fan[][] fan;
	protected Humidifier[][] humidifier;
	protected Light[][] light;
	protected Stand[][] stand;
	int[][] possible;
	int tempLogical;
	int brightLogical;
	int humiLogical;
	boolean Occupied;
	int[][][] change;
	RoomOccupied ro;
	RoomCost roomCost;
	int bestCost;
	int state;
	int time;

	private ArrayList<Device> devicesOn = new ArrayList<Device>();
	private ArrayList<Device> devicesOff = new ArrayList<Device>();
	private ArrayList<Device> devicesDehumi = new ArrayList<Device>();
	private ArrayList<Device> devicesWarm = new ArrayList<Device>();
	private ArrayList<Device> devicesCool = new ArrayList<Device>();

	public Room(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void init(RoomOccupied ro, int state) {
		Roominit ri = new Roominit(row, col);
		this.state = 0;
		ri.roomspec(state);
		this.ro = ro;

		tempMass = ri.getTempMass();
		brightMass = ri.getBrightMass();
		humiMass = ri.getHumiMass();
		has_window = ri.getHas_window();
		TL = ri.getTL();
		SL = ri.getSL();
		A = ri.getA();
		F = ri.getF();
		BC = ri.getBC();
		H = ri.getH();
		occupied = ri.getOccupied();
		aircon = ri.getAircon();
		blindcontroller = ri.getBlindcontroller();
		fan = ri.getFan();
		humidifier = ri.getHumidifier();
		light = ri.getLight();
		stand = ri.getStand();
		possible = new int[row][col];
		change = new int[row][col][3];

		useTL = new String[row][col][24];
		useSL = new String[row][col][24];
		useA = new String[row][col][24];
		useF = new String[row][col][24];
		useBC = new String[row][col][24];
		useH = new String[row][col][24];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				for (int k = 0; k < 24; k++) {
					useTL[i][j][k] = "";
					useSL[i][j][k] = "";
					useA[i][j][k] = "";
					useF[i][j][k] = "";
					useBC[i][j][k] = "";
					useH[i][j][k] = "";
				}
			}
		}
	}

	public int getBestCost() {
		return bestCost;
	}

	// 환경 요소에 대한 getter
	public boolean getHasWindow(int xPos, int yPos) {
		return has_window[xPos][yPos];
	}

	public String getUseSL(int i, int j, int time) {
		return useSL[i][j][time];
	}

	public String getUseTL(int i, int j, int time) {
		return useTL[i][j][time];
	}

	public String getUseA(int i, int j, int time) {
		return useA[i][j][time];
	}

	public String getUseF(int i, int j, int time) {
		return useF[i][j][time];
	}

	public String getUseBC(int i, int j, int time) {
		return useBC[i][j][time];
	}

	public String getUseH(int i, int j, int time) {
		return useH[i][j][time];
	}

	public boolean getH(int xPos, int yPos) {
		return H[xPos][yPos];
	}

	public boolean getBC(int xPos, int yPos) {
		return BC[xPos][yPos];
	}

	public boolean getF(int xPos, int yPos) {
		return F[xPos][yPos];
	}

	public boolean getA(int xPos, int yPos) {
		return A[xPos][yPos];
	}

	public boolean getSL(int xPos, int yPos) {
		return SL[xPos][yPos];
	}

	public boolean getTL(int xPos, int yPos) {
		return TL[xPos][yPos];
	}

	public double getTemperature(int xPos, int yPos) {
		return tempMass[xPos][yPos];
	}

	public double getHumidity(int xPos, int yPos) {
		return humiMass[xPos][yPos];
	}

	public double getBright(int xPos, int yPos) {
		return brightMass[xPos][yPos];
	}

	public boolean getOccupied(int xPos, int yPos) {
		return occupied[xPos][yPos];
	}

	public void setTempMass(int xPos, int yPos, double tempMass) {
		this.tempMass[xPos][yPos] = tempMass;
	}

	public void setBrightMass(int xPos, int yPos, double brightMass) {
		this.brightMass[xPos][yPos] = brightMass;
	}

	public void setHumiMass(int xPos, int yPos, double humiMass) {
		this.humiMass[xPos][yPos] = humiMass;
	}

	// 사무실 방의 사용여부를 확인하고 사용시 list에서 제외
	public int[][] checkOccupied(int row, int col, boolean[][] occupied,
			int[][] possible, int start_time, int end_time) {
		// ro = new RoomOccupied(row,col);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (occupied[i][j] == true
						&& checkTime(i, j, start_time, end_time)) {
					possible[i][j] = 0;
					ro.setTemp(tempMass);
					ro.setBright(brightMass);
					ro.setTemp(tempMass);
				} else
					possible[i][j] = 2;
			}
		}
		return possible;
	}

	public boolean checkTime(int i, int j, int start_time, int end_time) {
		if (ro.getStart_time(i, j, start_time) <= start_time) {
			if (start_time < ro.getEnd_time(i, j, start_time)) {
				return true;
			}
		}
		return false;
	}

	// 사용자의 요청에 따른 비교
	public int[] clientCall(String name, double temp, String tempState,
			double bright, String brightState, double humi, String humiState,
			RoomOccupied ro, int start_time, int end_time) {
		int[] useRoom = new int[2];
		RoomEnvChecker rec = new RoomEnvChecker();
		time = 0;

		// 사용자의 요청과 맞는 환경 값을 가지는 곳을 2로 표시
		possible = checkOccupied(row, col, occupied, possible, start_time,
				end_time);
		possible = rec.clientTmepCall(row, col, temp, tempState, tempMass,
				possible);
		possible = rec.clientBrightCall(row, col, bright, brightState,
				brightMass, possible);
		possible = rec.clientHumiCall(row, col, humi, humiState, humiMass,
				possible);

		// possible이 2인 값중에서 가장 첫번째 부분을 선정
		// 배치가 되는 경우 자리의 위치를 반환
		// 배치가 안되는 경우 null 반환
		// 가장 최적의 배치이며, 이 경우 어떠한 기기도 작동되지 않음
		useRoom = useRoom();

		// possible이 2인 값이 없는 경우 (possible이 모두 0 or 1인 경우)
		// possible == 0 : occupied되어 사용이 불가능 한 경우
		// possible == 1 : 사용자의 환경 조건에 의해서 사용이 불가능 한 경우
		if (useRoom == null) {
			// checkEnv를 통해 return 되어지는 possible 배열 중 변경이 가능한 곳은 값이 3인 곳
			possible = rec.checkEnv(temp, bright, humi, row, col, possible,
					tempMass, brightMass, humiMass, TL, SL, A, F, BC, H);

			// 어떤 환경을 변경 할지 setting
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (possible[i][j] == 3) {
						change = rec.checkEnvChange(temp, bright, humi, row,
								col, tempMass, brightMass, humiMass, i, j,
								change);
					}
				}
			}

			roomCost = new RoomCost(temp, bright, humi, change, tempMass,
					brightMass, humiMass, TL, SL, A, F, BC, H, row, col,
					possible, tempState, brightState, humiState, ro);

			roomCost.calculate(start_time, end_time);

			devicesCool = roomCost.getListCool();
			devicesDehumi = roomCost.getListDehumi();
			devicesOff = roomCost.getListOff();
			devicesOn = roomCost.getListOn();
			devicesWarm = roomCost.getListWarm();

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					for (int k = 0; k < 24; k++) {
						useTL[i][j][k] = roomCost.getUseTL(i, j, k);
						useSL[i][j][k] = roomCost.getUseSL(i, j, k);
						useA[i][j][k] = roomCost.getUseA(i, j, k);
						useF[i][j][k] = roomCost.getUseF(i, j, k);
						useBC[i][j][k] = roomCost.getUseBC(i, j, k);
						useH[i][j][k] = roomCost.getUseH(i, j, k);
					}
				}
			}

			useRoom = roomCost.bestRoom();
			occupied[useRoom[0]][useRoom[1]] = true;
			for (int i = start_time; i <= end_time; i++) {
				ro.setName(useRoom[0], useRoom[1], i, name);
				ro.setStart_time(useRoom[0], useRoom[1], i, start_time);
				ro.setEnd_time(useRoom[0], useRoom[1], i, end_time);
			}
			bestCost = roomCost.getCostRow();
			time = roomCost.getCountLow();
		}
		return useRoom;
	}

	// actuator 동작에 대한 환경변화 메소드 호출
	public void reflectChanges(int elapseTime) {
		for (Device device : devicesOn) {
			device.on(tempMass, brightMass, humiMass, elapseTime);
		}
		for (Device device : devicesOff) {
			device.off(tempMass, brightMass, humiMass, elapseTime);
		}
		for (Device device : devicesDehumi) {
			device.dehumidify(tempMass, brightMass, humiMass, elapseTime);
		}
		for (Device device : devicesWarm) {
			device.warm(tempMass, brightMass, humiMass, elapseTime);
		}
		for (Device device : devicesCool) {
			device.cool(tempMass, brightMass, humiMass, elapseTime);
		}
	}

	// 방을 탐색하여서 사용할 수 있는 방=이 존재한다면
	// 해당 방의 위치를 반환
	public int[] useRoom() {
		int[] useRoom = new int[2];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (possible[i][j] == 2) {
					occupied[i][j] = true;
					useRoom[0] = i;
					useRoom[1] = j;
					return useRoom;
				}
			}
		}
		return null;
	}

	public int timeCount() {
		return time;
	}
}
