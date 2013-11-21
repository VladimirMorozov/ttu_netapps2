package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import data.*;

public class Test {
	
	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException {
        // send data to the servlet
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
