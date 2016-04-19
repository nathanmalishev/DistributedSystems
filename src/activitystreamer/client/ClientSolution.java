package activitystreamer.client;

import activitystreamer.util.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientSolution extends Thread {
	private static final Logger log = LogManager.getLogger();
	private static ClientSolution clientSolution;
	private TextFrame textFrame;
	
	/*
	 * additional variables
	 */
    private Socket connection;
	
	// this is a singleton object
	public static ClientSolution getInstance(){
		if(clientSolution==null){
			clientSolution = new ClientSolution();
		}
		return clientSolution;
	}
	
	public ClientSolution(){
		/*
		 * some additional initialization
		 */
		try{
			connection = new Socket(Settings.getLocalHostname(), Settings.getRemotePort());
			System.out.print("connection started to server ");
		}catch(Exception e){
			System.out.print(e);
		}

		// open the gui
		log.debug("opening the gui");
		textFrame = new TextFrame();
		// start the client's thread
		start();
	}
	
	// called by the gui when the user clicks "send"
	public void sendActivityObject(JSONObject activityObj){
//		try{
//			BufferedOutputStream bos = new BufferedOutputStream(connection.
//					getOutputStream());
//			OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
//			osw.write("This is where the message would go");
//			osw.flush();
//		}catch(Exception e){
//			System.out.print(e);
//		}

	}
	
	// called by the gui when the user clicks disconnect
	public void disconnect(){
		textFrame.setVisible(false);
		/*
		 * other things to do
		 */
	}
	

	// the client's run method, to receive messages
	@Override
	public void run(){
		
	}

	/*
	 * additional methods
	 */
	
}
