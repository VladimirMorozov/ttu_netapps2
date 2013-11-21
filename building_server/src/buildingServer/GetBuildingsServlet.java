package buildingServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import data.*;

@SuppressWarnings("serial")
public class GetBuildingsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		Object object = readInputObject(req, resp);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<Building> result = null;
	    try {
	    	Query q = pm.newQuery(Building.class);
	    	result = new ArrayList<Building>((List<Building>)q.execute());
	    	for(Building b : result) {
	    		System.out.println(b);
	    	}
	    	
	    } finally {
	        pm.close();
	    }
	    outputObject(resp, result);
		
		//outputObject(resp, buildings);
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
}
