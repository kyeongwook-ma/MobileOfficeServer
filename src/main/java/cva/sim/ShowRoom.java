package cva.sim;

import java.util.Random;

import cva.gson.Response;
import cva.roomConfig.Room;
import cva.roomConfig.RoomOccupied;

public class ShowRoom {
	static final int Roomrow = 4;
	static final int Roomcol = 4;

	Room myRoom = new Room(Roomrow, Roomcol);
	RoomOccupied ro = new RoomOccupied(Roomrow, Roomcol);
	int count = 0;
	int state = 1;

	static void printRoom(Room myRoom) {
		System.out.println("Temperature");
		for (int i = 0; i < Roomrow; i++) {
			for (int j = 0; j < Roomcol; j++) {
				System.out.format("%5.2f, ", myRoom.getTemperature(i, j));
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Bright");
		for (int i = 0; i < Roomrow; i++) {
			for (int j = 0; j < Roomcol; j++) {
				System.out.format("%5.2f, ", myRoom.getBright(i, j));
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Humidity");
		for (int i = 0; i < Roomrow; i++) {
			for (int j = 0; j < Roomcol; j++) {
				System.out.format("%5.2f, ", myRoom.getHumidity(i, j));
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Occupied");
		for (int i = 0; i < Roomrow; i++) {
			for (int j = 0; j < Roomcol; j++) {
				System.out.format("%s, ", myRoom.getOccupied(i, j));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void init(int state){
		
		if(count == 0){
			myRoom.init(ro, state);
		}		
		
		for(int i=0; i<Roomrow; i++){
			for(int j=0; j<Roomcol; j++){
				double temp = myRoom.getTemperature(i, j);
				double bright = myRoom.getBright(i, j);
				double humi = myRoom.getHumidity(i, j);
				
				
				myRoom.setTempMass(i,j,change(temp));
				myRoom.setBrightMass(i, j, change(bright));
				myRoom.setHumiMass(i, j, change(humi));				
			}
		}		
		
		count++;
	}
	
	public double change(double envData){
		Random value = new Random();	
		Random logical = new Random();
		
		if(logical.nextInt(1) == 0){
			envData += value.nextDouble();
		}
		else
			envData -= value.nextDouble();
		
		return envData;
	}

	public String start(String name, String t, String tl, String b, String bl, String h, String hl) {

		if (count == 0) {
			init(state);		
			count++;
		}
		
		double temp = Double.parseDouble(t);
		double bright = Double.parseDouble(b);
		double humi = Double.parseDouble(h);

		int result[] = new int[2];
		printRoom(myRoom);
		result = myRoom.clientCall(name, temp, tl, bright, bl, humi, hl, ro);
		System.out.println();

		System.out.println("Result row : " + result[0] + " Result col : "
				+ result[1]);
		System.out.println();

		myRoom.reflectChanges(myRoom.timeCount());

		printRoom(myRoom);
		System.out.println();

		Response rs = new Response(myRoom, ro, Roomrow, Roomcol);

		return rs.call();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
