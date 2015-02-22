package cva.roomConfig;

public class RoomEnvChecker {

	int tempLogical;
	int brightLogical;
	int humiLogical;

	// Possible state
	// 0 : 완전 제외 [배치 불가능]
	// 1 : 예비 배치 [경우 확인 후 배치 가능 여부 파악]
	// 2 : 배치 가능 [배치가 가능한 상태]

	// 사용자의 요청에 따른 비교
	// 사용자의 요청과 일치하는 방을 찾고
	// 방의 정보와 요청이 일치하면 2, 아니면 1
	public int[][] clientTmepCall(int row, int col, double preference,
			String State, double[][] tempMass, int[][] possible) {
		tempLogical = checkState(State);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (possible[i][j] != 0 && possible[i][j] != 1) {
					switch (tempLogical) {
					case 1:
						if (preference == tempMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 2:
						if (preference <= tempMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 3:
						if (preference >= tempMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 4:
						if (preference > tempMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 5:
						if (preference < tempMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					}
				}
			}
		}
		return possible;
	}

	// 사용자의 요청에 따른 비교
	// 사용자의 요청과 일치하는 방을 찾고
	// 방의 정보와 요청이 일치하면 2, 아니면 1
	public int[][] clientBrightCall(int row, int col, double preference,
			String State, double[][] brightMass, int[][] possible) {
		brightLogical = checkState(State);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (possible[i][j] != 0 && possible[i][j] != 1) {
					switch (brightLogical) {
					case 1:
						if (preference == brightMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 2:
						if (preference <= brightMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 3:
						if (preference >= brightMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 4:
						if (preference > brightMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 5:
						if (preference < brightMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					}
				}
			}
		}
		return possible;
	}

	// 사용자의 요청에 따른 비교
	// 사용자의 요청과 일치하는 방을 찾고
	// 방의 정보와 요청이 일치하면 2, 아니면 1
	public int[][] clientHumiCall(int row, int col, double preference,
			String State, double[][] humiMass, int[][] possible) {
		humiLogical = checkState(State);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (possible[i][j] != 0 && possible[i][j] != 1) {
					switch (humiLogical) {
					case 1:
						if (preference == humiMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 2:
						if (preference <= humiMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 3:
						if (preference >= humiMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 4:
						if (preference > humiMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					case 5:
						if (preference < humiMass[i][j]) {
							possible[i][j] = 2;
						} else
							possible[i][j] = 1;
						break;
					}
				}
			}
		}
		return possible;
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

	// 방의 환경 변경가능 여부 확인
	public int[][] checkEnv(double temp, double bright, double humi, int row,
			int col, int[][] possible, double[][] tempMass,
			double[][] brightMass, double[][] humiMass, boolean[][] TL,
			boolean[][] SL, boolean[][] A, boolean[][] F, boolean[][] BC,
			boolean[][] H) {
		double tempresult;
		double brightresult;
		double humiresult;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (possible[i][j] == 1) {
					tempresult = tempMass[i][j] - temp; // 0
					brightresult = brightMass[i][j] - bright; // - 10
					humiresult = humiMass[i][j] - humi; // 0

					if (checkTemp(tempresult, i, j, TL, SL, A, F, BC, H)
							&& checkBright(brightresult, i, j, TL, SL, A, F,
									BC, H)
							&& checkHumi(humiresult, i, j, TL, SL, A, F, BC, H)) {
						possible[i][j] = 3;
					} else
						possible[i][j] = 4;
				}
			}
		}
		return possible;
	}

	// 변경해야 할 환경 요소 확인
	// checkChage[][][] = 0  변경이 없어도 됨
	// checkChage[][][] = 1  변경이 필요 환경 값을 올려야 함
	// checkChage[][][] = 2  변경이 필요 환경 값을 내려야 함
	// checkChage[][][0] = temp
	// checkChage[][][1] = bright
	// checkChage[][][2] = humi
	public int[][][] checkEnvChange(double temp, double bright, double humi,
			int row, int col, double[][] tempMass, double[][] brightMass,
			double[][] humiMass, int x, int y, int[][][] checkChage) {
		double tempresult;
		double brightresult;
		double humiresult;

		tempresult = tempMass[x][y] - temp; // 0
		brightresult = brightMass[x][y] - bright; // - 10 
		humiresult = humiMass[x][y] - humi; // 0

		if (this.tempLogical == 1) {
			if (tempresult < 0 ) {
				checkChage[x][y][0] = 1;
			} else if(tempresult > 0){
				checkChage[x][y][0] = 2;
			} else
				checkChage[x][y][0] = 0;
		} else if (this.tempLogical == 2) {
			if (tempresult < 0) {
				checkChage[x][y][0] = 1;
			} else
				checkChage[x][y][0] = 0;
		} else if (this.tempLogical == 3) {
			if (tempresult > 0) {
				checkChage[x][y][0] = 2;
			} else
				checkChage[x][y][0] = 0;
		} else if (this.tempLogical == 4) {
			if (tempresult >= 0) {
				checkChage[x][y][0] = 2;
			} else
				checkChage[x][y][0] = 0;
		} else if (this.tempLogical == 5) {
			if (tempresult <= 0) {
				checkChage[x][y][0] = 1;
			} else
				checkChage[x][y][0] = 0;
		}

		if (this.brightLogical == 1) {
			if (brightresult < 0 ) {
				checkChage[x][y][1] = 1;
			} else if(brightresult > 0) {
				checkChage[x][y][1] = 2;
			} else
				checkChage[x][y][1] = 0;				
		} else if (this.brightLogical == 2) {
			if (brightresult < 0) {
				checkChage[x][y][1] = 1;
			} else
				checkChage[x][y][1] = 0;
		} else if (this.brightLogical == 3) {
			if (brightresult > 0) {
				checkChage[x][y][1] = 2;
			} else
				checkChage[x][y][1] = 0;
		} else if (this.brightLogical == 4) {
			if (brightresult >= 0) {
				checkChage[x][y][1] = 2;
			} else
				checkChage[x][y][1] = 0;
		} else if (this.brightLogical == 5) {
			if (brightresult <= 0) {
				checkChage[x][y][1] = 1;
			} else
				checkChage[x][y][1] = 0;
		}

		if (this.humiLogical == 1) {
			if (humiresult < 0 ) {
				checkChage[x][y][2] = 1;
			} else if (humiresult > 0) {
				checkChage[x][y][2] = 2;
			} else {
				checkChage[x][y][2] = 0;
			}
		} else if (this.humiLogical == 2) {
			if (humiresult < 0) {
				checkChage[x][y][2] = 1;
			} else
				checkChage[x][y][2] = 0;
		} else if (this.humiLogical == 3) {
			if (humiresult > 0) {
				checkChage[x][y][2] = 2;
			} else
				checkChage[x][y][2] = 0;
		} else if (this.humiLogical == 4) {
			if (humiresult >= 0) {
				checkChage[x][y][2] = 2;
			} else
				checkChage[x][y][2] = 0;
		} else if (this.humiLogical == 5) {
			if (humiresult <= 0) {
				checkChage[x][y][2] = 1;
			} else
				checkChage[x][y][2] = 0;
		}
		return checkChage;
	}

	// 입력된 온도에 따라서 기기가 사용가능 하는가의 여부
	public boolean checkTemp(double tempresult, int i, int j, boolean[][] TL,
			boolean[][] SL, boolean[][] A, boolean[][] F, boolean[][] BC,
			boolean[][] H) {
		switch (this.tempLogical) {
		case 1:
			if (tempresult > 0) {
				if (A[i][j] == true) {
					return true;
				}
				if (F[i][j] == true) {
					return true;
				}
			} else if (tempresult < 0) {
				if (A[i][j] == true) {
					return true;
				}
				if (TL[i][j] == true) {
					return true;
				}
				if (BC[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
				if (F[i][j] == true) {
					return true;
				}
			} else if (tempresult == 0) {
				return true;
			}
			break;
		case 2:
			if (tempresult < 0) {
				if (A[i][j] == true) {
					return true;
				}
				if (TL[i][j] == true) {
					return true;
				}
				if (BC[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
				if (F[i][j] == true) {
					return true;
				}
			} else if (tempresult >= 0) {
				return true;
			}
			break;
		case 3:
			if (tempresult > 0) {
				if (A[i][j] == true) {
					return true;
				}
				if (F[i][j] == true) {
					return true;
				}
			} else if (tempresult <= 0) {
				return true;
			}
			break;
		case 4:
			if (tempresult >= 0) {
				if (A[i][j] == true) {
					return true;
				}
				if (TL[i][j] == true) {
					return true;
				}
				if (BC[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
				if (F[i][j] == true) {
					return true;
				}
			} else if (tempresult < 0) {
				return true;
			}
			break;
		case 5:
			if (tempresult <= 0) {
				if (A[i][j] == true) {
					return true;
				}
				if (F[i][j] == true) {
					return true;
				}
			} else if (tempresult > 0) {
				return true;
			}
			break;
		}
		return false;
	}

	public boolean checkBright(double brightresult, int i, int j,
			boolean[][] TL, boolean[][] SL, boolean[][] A, boolean[][] F,
			boolean[][] BC, boolean[][] H) {
		switch (this.brightLogical) {
		case 1:
			if (brightresult > 0) {
				if (TL[i][j] == true) {
					return true;
				}
				if (BC[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
			} else if (brightresult < 0) {
				if (TL[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
			} else if (brightresult == 0) {
				return true;
			}
			break;
		case 2:
			if (brightresult < 0) {
				if (TL[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
			} else if (brightresult >= 0) {
				return true;
			}
			break;
		case 3:
			if (brightresult > 0) {
				if (TL[i][j] == true) {
					return true;
				}
				if (BC[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
			} else if (brightresult <= 0) {
				return true;
			}
			break;
		case 4:
			if (brightresult >= 0) {
				if (TL[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
			} else if (brightresult < 0) {
				return true;
			}
			break;
		case 5:
			if (brightresult <= 0) {
				if (TL[i][j] == true) {
					return true;
				}
				if (BC[i][j] == true) {
					return true;
				}
				if (SL[i][j] == true) {
					return true;
				}
			} else if (brightresult > 0) {
				return true;
			}
			break;
		}
		return false;
	}

	public boolean checkHumi(double humiresult, int i, int j, boolean[][] TL,
			boolean[][] SL, boolean[][] A, boolean[][] F, boolean[][] BC,
			boolean[][] H) {
		switch (this.humiLogical) {
		case 1:
			if (humiresult > 0) {
				if (A[i][j] == true) {
					return true;
				}
			} else if (humiresult < 0) {
				if (H[i][j] == true) {
					return true;
				}
			} else if (humiresult == 0) {
				return true;
			}
			break;
		case 2:
			if (humiresult < 0) {
				if (H[i][j] == true) {
					return true;
				}
			} else if (humiresult >= 0) {
				return true;
			}
			break;
		case 3:
			if (humiresult > 0) {
				if (A[i][j] == true) {
					return true;
				}
			} else if (humiresult <= 0) {
				return true;
			}
			break;
		case 4:
			if (humiresult >= 0) {
				if (A[i][j] == true) {
					return true;
				}
			} else if (humiresult < 0) {
				return true;
			}
			break;
		case 5:
			if (humiresult <= 0) {
				if (H[i][j] == true) {
					return true;
				}
			} else if (humiresult > 0) {
				return true;
			}
			break;
		}
		return false;
	}
}
