package states.subscriber;


/**
 * @author Fahreen Bushra 
 */

import events.AbstractEvent;

public class AState implements IState {

	public void handleEvent(AbstractEvent event, String channelName) {
		
		//check if the right thing is printed
		System.out.println("Event "+ event.hashCode() + " in channel " + channelName + " is handled by Astate");	
	}
	

}


