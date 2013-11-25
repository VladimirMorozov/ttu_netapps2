package test;

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

import com.google.appengine.api.datastore.Key;

import data.*;

public class Test {
	
	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException {
		generateBuilding();
		List<Building> buildings = getBuildings();
		//
		/*Room newRoom = new Room();
		newRoom.setFunction("func test - created on "+new Date());
		newRoom.setNumber(999);
		addRoom(buildings.get(0).getKey(), newRoom);
		getBuildings();*/
		
		Room editedRoom = buildings.get(1).getRooms().get(0);
		System.out.println("before: " + editedRoom);
		editedRoom.setFunction("func test - EDITED on " + new Date());
		editExistingRoom(editedRoom);
		getBuildings();
	}

	public static List<Building> getBuildings() throws MalformedURLException,
			IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection("http://localhost:8888/GetBuildingsServlet");
        OutputStream outstream = con.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
        System.out.println(oos);
        oos.writeObject("dummy object");
        oos.flush();
        oos.close();

        // receive result from servlet
        InputStream instr = con.getInputStream();
        ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
        List<Building> result = (List<Building>) inputFromServlet.readObject();
        inputFromServlet.close();
        instr.close();
        
        System.out.println("getBuildings received:\n"+result);
        return result;
	}
	
	public static void addRoom(Key buildingKey, Room roomToAdd) throws MalformedURLException, IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection("http://localhost:8888/SaveRoomServlet");
        OutputStream outstream = con.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
        
        oos.writeObject(roomToAdd);
        oos.writeObject(buildingKey);
        oos.flush();
        oos.close();

        // receive result from servlet
        InputStream instr = con.getInputStream();
        ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
        String result = (String) inputFromServlet.readObject();
        inputFromServlet.close();
        instr.close();
        
        System.out.println(result);
	}
	
	public static void editExistingRoom(Room roomToUpdate) throws MalformedURLException, IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection("http://localhost:8888/EditRoomServlet");
        OutputStream outstream = con.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
        
        oos.writeObject(roomToUpdate);
        oos.flush();
        oos.close();

        // receive result from servlet
        InputStream instr = con.getInputStream();
        ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
        String result = (String) inputFromServlet.readObject();
        inputFromServlet.close();
        instr.close();
        
        System.out.println(result);
	}
	
	public static void generateBuilding() throws MalformedURLException, IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection("http://localhost:8888/SaveBuildingServlet");
        OutputStream outstream = con.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
        
        Building building = new Building();
        building.setAddress("Some address");
        building.setFloors(5L);
        oos.writeObject(building);
        oos.flush();
        oos.close();

        // receive result from servlet
        InputStream instr = con.getInputStream();
        ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
        Key result = (Key) inputFromServlet.readObject();
        inputFromServlet.close();
        instr.close();
        
        System.out.println(result);
	}
	
	private static URLConnection getServletConnection(String url)
	          throws MalformedURLException, IOException {

	          // Connection zum Servlet ?ffnen
	          URL urlServlet = new URL(url);
	          URLConnection con = urlServlet.openConnection();

	          // konfigurieren
	          con.setDoInput(true);
	          con.setDoOutput(true);
	          con.setUseCaches(false);
	          con.setRequestProperty(
	               "Content-Type",
	               "application/x-java-serialized-object");

	          // und zur?ckliefern
	          return con;
	     }

}
