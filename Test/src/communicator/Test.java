package communicator;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import data.*;
import static communicator.Communicator.*;


public class Test {
	
	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException {
		//
		
		generateBuilding();
		
		List<Building> buildings = getBuildings();
		
		//
		/*Room newRoom = new Room();
		newRoom.setFunction("func test - created on "+new Date());
		newRoom.setNumber(999);
		addRoom(buildings.get(0).getKey(), newRoom);
		getBuildings();*/
		
		//Activity newActivity = new Activity();
		//newActivity.setName("yay" + new Date());
		
		//addActivity(buildings.get(1).getRooms().get(0).getKey(), newActivity);
		
		/*Room editedRoom = buildings.get(1).getRooms().get(0);
		System.out.println("before: " + editedRoom);
		editedRoom.setFunction("func test - EDITED on " + new Date());
		editExistingRoom(editedRoom);
		getBuildings();*/
		/*
		buildings.get(0).setAddress("EDITED ADDRESS");
		editExistingBuilding(buildings.get(0));
		
		*/
		//buildings = getBuildings();
	}

}
