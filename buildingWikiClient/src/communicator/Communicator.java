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

public class Communicator {
	
	//public static String SERVER_URL = "http://localhost:8888";
	public static String SERVER_URL = "http://1.vladimirrateapp.appspot.com";
	

	public static List<Building> getBuildings() throws MalformedURLException,
			IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection(SERVER_URL+"/GetBuildingsServlet");
        OutputStream outstream = con.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
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
	
	public static void addRoom(String buildingKey, Room roomToAdd) throws MalformedURLException, IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection(SERVER_URL+"/SaveRoomServlet");
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
	
	public static void addActivity(String roomKey, Activity activityToAdd) throws MalformedURLException, IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection(SERVER_URL+"/SaveActivityServlet");
        OutputStream outstream = con.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
        
        oos.writeObject(activityToAdd);
        oos.writeObject(roomKey);
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
		URLConnection con = getServletConnection(SERVER_URL+"/EditRoomServlet");
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
	
	public static void editExistingBuilding(Building buildingToUpdate) throws MalformedURLException, IOException, ClassNotFoundException {
		//send
		URLConnection con = getServletConnection(SERVER_URL+"/EditBuildingServlet");
        OutputStream outstream = con.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
        
        oos.writeObject(buildingToUpdate);
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
		URLConnection con = getServletConnection(SERVER_URL+"/SaveBuildingServlet");
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
        String result = (String) inputFromServlet.readObject();
        inputFromServlet.close();
        instr.close();
        
        System.out.println(result);
	}
	
	private static URLConnection getServletConnection(String url)
	          throws MalformedURLException, IOException {

	          URL urlServlet = new URL(url);
	          URLConnection con = urlServlet.openConnection();

	          con.setDoInput(true);
	          con.setDoOutput(true);
	          con.setUseCaches(false);
	          con.setRequestProperty(
	               "Content-Type",
	               "application/x-java-serialized-object");

	          return con;
	     }

}
