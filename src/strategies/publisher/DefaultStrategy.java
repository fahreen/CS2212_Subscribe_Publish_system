package strategies.publisher;


/**
 * @author Fahreen Bushra 
 */

import java.util.ArrayList;
import java.util.List;

import events.AbstractEvent;
import events.EventFactory;
import events.EventMessage;
import events.EventType;
import pubSubServer.Access;
import pubSubServer.ChannelEventDispatcher;

public class DefaultStrategy implements IStrategy {

	@Override
	public void doPublish(int publisherId) {
		// create list for channels
			List<String> channelList = new ArrayList<String>();
			
			// add channels
			channelList.add("general");
			channelList.add("main");
			AbstractEvent event = EventFactory.createEvent(EventType.TypeC, publisherId, new EventMessage("help!" , "please!!!"));
			//Output (8) Channel x has event y from publisher x
			System.out.println("Channel general has event " + event.hashCode() + " from publisher " + publisherId ); 
			System.out.println("Channel main has event " + event.hashCode() + " from publisher " + publisherId ); 
			Access getter = new Access();
			ChannelEventDispatcher ced = getter.getced();
			ced.postEvent(event, channelList) ;
			
	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		//Output (8) Channel x has event y from publisher x
		System.out.println("Channel general has event " + event.hashCode() + " from publisher " + publisherId ); 
		System.out.println("Channel main has event " + event.hashCode() + " from publisher " + publisherId ); 
		List<String> channelList = new ArrayList<String>();
		// add channels
		channelList.add("general");
		channelList.add("main");
		Access getter = new Access();
		ChannelEventDispatcher ced = getter.getced();
		ced.postEvent(event, channelList) ;
		
		
	}
	

}
