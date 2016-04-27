package activitystreamer.messages;

/**
 * Created by Jeames on 27/04/2016.
 */
public class ActivityMessage extends JsonMessage {

    private String username;
    private String secret;
    private String activity;

    public ActivityMessage(String username, String secret, String activity) {

        this.command = "ACTIVITY_MESSAGE";
        this.username = username;
        this.secret = secret;
        this.activity = activity;
    }


}
