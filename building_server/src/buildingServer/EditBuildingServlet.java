package buildingServer;

import java.io.*;

import javax.jdo.*;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Key;

import data.*;

@SuppressWarnings("serial")
public class EditBuildingServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Object input = readInputObject(req, resp);
		Building building = (Building)input;
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	    	/*Room originalRoom = pm.getObjectById(Room.class, room.getKey());
	    	room.setActivities(originalRoom.getActivities());*/
	    	pm.makePersistent(building);
	    	
	    	outputObject(resp, "ok");
	    } finally {
	        pm.close();
	    }
	}
	
	private void outputObject(HttpServletResponse resp, Object toReturn)
			throws IOException {
		OutputStream output = resp.getOutputStream();
		ObjectOutputStream objectOutput = new ObjectOutputStream(output);
		objectOutput.writeObject(toReturn);
		objectOutput.flush();
		objectOutput.close();
	}
	
	private Object readInputObject(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		System.out.println("doPost");
		resp.setContentType("applicaton/x-java-serialized-object");
		InputStream in = req.getInputStream();
		ObjectInputStream objectIn = new ObjectInputStream(in);
		Object receivedObject = null;
		try {
			receivedObject = objectIn.readObject();
			System.out.println(receivedObject);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		objectIn.close();
		return receivedObject;
	}
	
	private Object[] readInputObjects(HttpServletRequest req,
			HttpServletResponse resp, int size) throws IOException {
		Object[] result = new Object[size];
		resp.setContentType("applicaton/x-java-serialized-object");
		InputStream in = req.getInputStream();
		ObjectInputStream objectIn = new ObjectInputStream(in);
		Object receivedObject = null;
		try {
			for(int i = 0; i < size; i++) {
				result[i] = objectIn.readObject();
				System.out.println(result[i]);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		objectIn.close();
		return result;
	}
	
}
