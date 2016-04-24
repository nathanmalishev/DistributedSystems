package activitystreamer.messages;

import com.google.gson.Gson;

public class JsonMessage {

	public static final String invalidMessageTypeError = "the message type was not recognised";

	protected String command;
	
	public String getCommand() {
		return command;
	}

	public String toData() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

}
