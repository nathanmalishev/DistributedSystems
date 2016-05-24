package activitystreamer.messages;

public class GetKey extends JsonMessage{
	
	private String serverId;

	public GetKey(String serverId){
		super();
		this.command = "GET_KEY";
		this.serverId = serverId;
	}
	
	public String getServerId() {return serverId;}
}
