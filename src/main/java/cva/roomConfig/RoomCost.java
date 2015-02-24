package cva.roomConfig;

import java.util.ArrayList;

import cva.deviceConfig.Aircon;
import cva.deviceConfig.BlindController;
import cva.deviceConfig.Device;
import cva.deviceConfig.Fan;
import cva.deviceConfig.Humidifier;
import cva.deviceConfig.Light;
import cva.deviceConfig.Stand;

public class RoomCost {

	private ArrayList<Device> devicesOn = new ArrayList<Device>();
	private ArrayList<Device> devicesOff = new ArrayList<Device>();
	private ArrayList<Device> devicesDehumi = new ArrayList<Device>();
	private ArrayList<Device> devicesWarm = new ArrayList<Device>();
	private ArrayList<Device> devicesCool = new ArrayList<Device>();

	private ArrayList<Device> listOn = new ArrayList<Device>();
	private ArrayList<Device> listOff = new ArrayList<Device>();
	private ArrayList<Device> listDehumi = new ArrayList<Device>();
	private ArrayList<Device> listWarm = new ArrayList<Device>();
	private ArrayList<Device> listCool = new ArrayList<Device>();

	double temp;
	double bright;
	double humi;
	int[][][] change;
	double[][] tempMass;
	double[][] brightMass;
	double[][] humiMass;
	double[][] tempOrigin;
	double[][] brightOrigin;
	double[][] humiOrigin;
	int[][] possible;
	boolean[][] TL;
	boolean[][] SL;
	boolean[][] A;
	boolean[][] F;
	boolean[][] BC;
	boolean[][] H;
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
	int row;
	int col;
	int count;
	int countrow;
	int tempLogical;
	int brightLogical;
	int humiLogical;
	int cost;
	int costRow = 999999;
	int xPos, yPos;
	RoomOccupied ro;
	Roominit ri;

	RoomCost(double temp, double bright, double humi, int[][][] change,
			double[][] tempMass, double[][] brightMass, double[][] humiMass,
			boolean[][] TL, boolean[][] SL, boolean[][] A, boolean[][] F,
			boolean[][] BC, boolean[][] H, int row, int col, int[][] possible,
			String tempState, String brightState, String humiState,
			RoomOccupied ro) {
		this.temp = temp;
		this.bright = bright;
		this.humi = humi;
		this.change = change;
		this.tempMass = tempMass;
		this.brightMass = brightMass;
		this.humiMass = humiMass;
		this.TL = TL;
		this.SL = SL;
		this.A = A;
		this.F = F;
		this.BC = BC;
		this.H = H;
		this.row = row;
		this.col = col;
		this.possible = possible;
		this.tempLogical = checkState(tempState);
		this.brightLogical = checkState(brightState);
		this.humiLogical = checkState(humiState);
		this.ro = ro;
		aircon = new Aircon[row][col];
		blindcontroller = new BlindController[row][col];
		fan = new Fan[row][col];
		humidifier = new Humidifier[row][col];
		light = new Light[row][col];
		stand = new Stand[row][col];
		useTL = new String[row][col][24];
		useSL = new String[row][col][24];
		useA = new String[row][col][24];
		useF = new String[row][col][24];
		useBC = new String[row][col][24];
		useH = new String[row][col][24];
	}

	public int getCostRow() {
		return costRow;
	}

	// actuator on
	public boolean on(Device divice) {
		return devicesOn.add(divice);
	}

	// actuator off
	public boolean off(Device device) {
		return devicesOff.add(device);
	}

	// actuator dehumidify
	public boolean dehumi(Device device) {
		return devicesDehumi.add(device);
	}

	// actuator warm
	public boolean warm(Device device) {
		return devicesWarm.add(device);
	}

	// actuator cool
	public boolean cool(Device device) {
		return devicesCool.add(device);
	}

	public boolean liston(Device device) {
		return listOn.add(device);
	}

	public boolean listoff(Device device) {
		return listOff.add(device);
	}

	public boolean listdehumi(Device device) {
		return listDehumi.add(device);
	}

	public boolean listwarm(Device device) {
		return listWarm.add(device);
	}

	public boolean listcool(Device device) {
		return listCool.add(device);
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

	public ArrayList<Device> getListOn() {
		return listOn;
	}

	public ArrayList<Device> getListOff() {
		return listOff;
	}

	public ArrayList<Device> getListDehumi() {
		return listDehumi;
	}

	public ArrayList<Device> getListWarm() {
		return listWarm;
	}

	public ArrayList<Device> getListCool() {
		return listCool;
	}

	public int getCountLow() {
		return countrow;
	}

	public void setListOn(ArrayList<Device> listOn) {
		this.listOn = listOn;
	}

	public void setListOff(ArrayList<Device> listOff) {
		this.listOff = listOff;
	}

	public void setListDehumi(ArrayList<Device> listDehumi) {
		this.listDehumi = listDehumi;
	}

	public void setListWarm(ArrayList<Device> listWarm) {
		this.listWarm = listWarm;
	}

	public void setListCool(ArrayList<Device> listCool) {
		this.listCool = listCool;
	}

	public void calculate(int start_time, int end_time) {

		tempOrigin = new double[row][col];
		brightOrigin = new double[row][col];
		humiOrigin = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tempOrigin[i][j] = tempMass[i][j];
				brightOrigin[i][j] = brightMass[i][j];
				humiOrigin[i][j] = humiMass[i][j];
			}
		}

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
				if (possible[i][j] == 3) {
					selectActuator(i, j); // 동작할 actuator 선택
					runActuator(i, j); // actuator 동작
					vsCost(cost, i, j, start_time, end_time);
				}
			}
		}
	}

	public void reset() {
		devicesOn.clear();
		devicesOff.clear();
		devicesDehumi.clear();
		devicesWarm.clear();
		devicesCool.clear();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tempMass[i][j] = tempOrigin[i][j];
				brightMass[i][j] = brightOrigin[i][j];
				humiMass[i][j] = humiOrigin[i][j];
			}
		}
		count = 0;
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
			device.dehumidify(tempMass, brightMass, this.humiMass, elapseTime);
		}
		for (Device device : devicesWarm) {
			device.warm(tempMass, brightMass, humiMass, elapseTime);
		}
		for (Device device : devicesCool) {
			device.cool(tempMass, brightMass, humiMass, elapseTime);
		}
	}

	public int calculateCostW(int elapseTime) {

		int costW = 0;

		for (Device device : devicesOn) {
			costW += device.costW(elapseTime);
		}
		for (Device device : devicesOff) {
			costW += device.costW(elapseTime);
		}
		for (Device device : devicesDehumi) {
			costW += device.costW(elapseTime);
		}
		for (Device device : devicesWarm) {
			costW += device.costW(elapseTime);
		}
		for (Device device : devicesCool) {
			costW += device.costW(elapseTime);
		}

		return costW;
	}

	public void runActuator(int i, int j) {
		// 사용자 요청에 맞는 환경이 될 때 까지 동작
		while (!equalEnv(i, j) && count < 10) {
			count++;
			reflectChanges(1);
		}

		cost = calculateCostW(count) + count + violation() * 100 * count;

	}

	public int violation() {
		int violation = 0;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (possible[i][j] == 0) {
					if (tempMass[i][j] != ro.getTemp(i, j)) {
						violation++;
					}
					if (brightMass[i][j] != ro.getBright(i, j)) {
						violation++;
					}
					if (humiMass[i][j] != ro.getHumi(i, j)) {
						violation++;
					}
				}
			}
		}

		return violation;
	}

	public void vsCost(int cost, int i, int j, int start_time, int end_time) {
		System.out.println("x: " + i);
		System.out.println("y: " + j);
		System.out.println("cost: " + cost);
		if (cost < this.costRow) {
			listOn.clear();
			listOff.clear();
			listDehumi.clear();
			listWarm.clear();
			listCool.clear();
			settingActuator(i, j, start_time, end_time);
			this.costRow = cost;
			this.countrow = this.count;
			this.xPos = i;
			this.yPos = j;
		} else {
			reset();
		}
	}

	public int[] bestRoom() {
		int[] useRoom = new int[2];

		useRoom[0] = this.xPos;
		useRoom[1] = this.yPos;

		return useRoom;
	}

	public boolean equalEnv(int i, int j) {
		boolean flag = false;

		if (checkTemp(temp, i, j) && checkBright(bright, i, j)
				&& checkHumi(humi, i, j)) {
			flag = true;
		}

		return flag;
	}

	// 사용자 요청의 Logical 부분 처리
	public int checkState(String state) {
		// 같은 경우
		if (state.equals("EQ"))
			return 1;
		// 같거나 큰 경우
		else if (state.equals("EG"))
			return 2;
		// 같거나 작은 경우
		else if (state.equals("EL"))
			return 3;
		// 작은 경우
		else if (state.equals("L"))
			return 4;
		// 큰 경우
		else if (state.equals("G"))
			return 5;
		return 0;
	}

	public boolean checkTemp(double tempresult, int i, int j) {
		switch (this.tempLogical) {
		case 1:
			if (tempresult == this.tempMass[i][j]) {
				return true;
			}
			break;
		case 2:
			if (tempresult <= this.tempMass[i][j]) {
				return true;
			}
			break;
		case 3:
			if (tempresult >= this.tempMass[i][j]) {
				return true;
			}
			break;
		case 4:
			if (tempresult > this.tempMass[i][j]) {
				return true;
			}
			break;
		case 5:
			if (tempresult < this.tempMass[i][j]) {
				return true;
			}
			break;
		}
		return false;
	}

	public boolean checkBright(double brightresult, int i, int j) {
		switch (this.brightLogical) {
		case 1:
			if (brightresult == brightMass[i][j]) {
				return true;
			}
			break;
		case 2:
			if (brightresult <= brightMass[i][j]) {
				return true;
			}
			break;
		case 3:
			if (brightresult >= brightMass[i][j]) {
				return true;
			}
			break;
		case 4:
			if (brightresult > brightMass[i][j]) {
				return true;
			}
			break;
		case 5:
			if (brightresult < brightMass[i][j]) {
				return true;
			}
			break;
		}
		return false;
	}

	public boolean checkHumi(double humiresult, int i, int j) {
		switch (this.humiLogical) {
		case 1:
			if (humiresult == humiMass[i][j]) {
				return true;
			}
			break;
		case 2:
			if (humiresult <= humiMass[i][j]) {
				return true;
			}
			break;
		case 3:
			if (humiresult >= humiMass[i][j]) {
				return true;
			}
			break;
		case 4:
			if (humiresult > humiMass[i][j]) {
				return true;
			}
			break;
		case 5:
			if (humiresult < humiMass[i][j]) {
				return true;
			}
			break;
		}
		return false;
	}

	public void selectActuator(int i, int j) {
		if (change[i][j][0] == 1) {
			if (A[i][j]) {
				aircon[i][j] = new Aircon(row, col, i, j);
				warm(aircon[i][j]);
			}
			if (TL[i][j]) {
				light[i][j] = new Light(row, col, i, j);
				on(light[i][j]);
			}
			if (BC[i][j]) {
				blindcontroller[i][j] = new BlindController(row, col, i, j);
				on(blindcontroller[i][j]);
			}
			if (SL[i][j]) {
				stand[i][j] = new Stand(row, col, i, j);
				on(stand[i][j]);
			}
		}
		if (change[i][j][1] == 1) {
			if (TL[i][j]) {
				light[i][j] = new Light(row, col, i, j);
				on(light[i][j]);
			}
			if (SL[i][j]) {
				stand[i][j] = new Stand(row, col, i, j);
				on(stand[i][j]);
			}
		}
		if (change[i][j][2] == 1) {
			if (H[i][j]) {
				humidifier[i][j] = new Humidifier(row, col, i, j);
				on(humidifier[i][j]);
			}
		}
		if (change[i][j][0] == 2) {
			if (A[i][j]) {
				aircon[i][j] = new Aircon(row, col, i, j);
				cool(aircon[i][j]);
			}
			if (F[i][j]) {
				fan[i][j] = new Fan(row, col, i, j);
				on(fan[i][j]);
			}
		}
		if (change[i][j][1] == 2) {
			if (TL[i][j]) {
				light[i][j] = new Light(row, col, i, j);
				off(light[i][j]);
			}
			if (SL[i][j]) {
				stand[i][j] = new Stand(row, col, i, j);
				off(stand[i][j]);
			}
			if (BC[i][j]) {
				blindcontroller[i][j] = new BlindController(row, col, i, j);
				off(blindcontroller[i][j]);
			}
		}
		if (change[i][j][2] == 2) {
			if (A[i][j]) {
				aircon[i][j] = new Aircon(row, col, i, j);
				dehumi(aircon[i][j]);
			}
		}
	}

	public void settingActuator(int i, int j, int start_time, int end_time) {
		if (change[i][j][0] == 1) {
			if (A[i][j]) {
				aircon[i][j] = new Aircon(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useA[i][j][k] = "AirconOn";
				listwarm(aircon[i][j]);
			}
			if (TL[i][j]) {
				light[i][j] = new Light(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useTL[i][j][k] = "LightOn";
				liston(light[i][j]);
			}
			if (BC[i][j]) {
				blindcontroller[i][j] = new BlindController(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useBC[i][j][k] = "BlindOn";
				liston(blindcontroller[i][j]);
			}
			if (SL[i][j]) {
				stand[i][j] = new Stand(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useSL[i][j][k] = "StandOn";
				liston(stand[i][j]);
			}
		}
		if (change[i][j][1] == 1) {
			if (TL[i][j]) {
				light[i][j] = new Light(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useTL[i][j][k] = "LightOn";
				liston(light[i][j]);
			}
			if (SL[i][j]) {
				stand[i][j] = new Stand(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useSL[i][j][k] = "StandOn";
				liston(stand[i][j]);
			}
		}
		if (change[i][j][2] == 1) {
			if (H[i][j]) {
				humidifier[i][j] = new Humidifier(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useH[i][j][k] = "HumiOn";
				liston(humidifier[i][j]);
			}
		}
		if (change[i][j][0] == 2) {
			if (A[i][j]) {
				aircon[i][j] = new Aircon(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useA[i][j][k] = "AirconOn";
				listcool(aircon[i][j]);
			}
			if (F[i][j]) {
				fan[i][j] = new Fan(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useF[i][j][k] = "FanOn";
				liston(fan[i][j]);
			}
		}
		if (change[i][j][1] == 2) {
			if (TL[i][j]) {
				light[i][j] = new Light(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useTL[i][j][k] = "LightOff";
				listoff(light[i][j]);
			}
			if (SL[i][j]) {
				stand[i][j] = new Stand(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useSL[i][j][k] = "StandOff";
				listoff(stand[i][j]);
			}
			if (BC[i][j]) {
				blindcontroller[i][j] = new BlindController(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useBC[i][j][k] = "BlindOff";
				listoff(blindcontroller[i][j]);
			}
		}
		if (change[i][j][2] == 2) {
			if (A[i][j]) {
				aircon[i][j] = new Aircon(row, col, i, j);
				for(int k=start_time; k<=end_time; k++)
				useA[i][j][k] = "AirconOn";
				listdehumi(aircon[i][j]);
			}
		}
	}
}
