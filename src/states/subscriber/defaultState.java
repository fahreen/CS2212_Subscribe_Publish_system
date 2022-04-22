package states.subscriber;


/**
 * @author Fahreen Bushra 
 */ 

 
import events.AbstractEvent;

public class defaultState implements IState{

	public void handleEvent(AbstractEvent event, String channelName) {
		
		System.out.println("Event "+ event.hashCode() + " in channel " + channelName + " is handled by defaultstate");
	}

}
