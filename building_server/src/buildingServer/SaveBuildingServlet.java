package buildingServer;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;

import data.Building;
import data.Room;

/**
 * !!! Haven't checked after restoring !!! 
 *
 */
@SuppressWarnings("serial")
public class SaveBuildingServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Object input = readInputObject(req, resp);
		Building building = (Building)input;
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
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
