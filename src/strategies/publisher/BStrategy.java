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



public class BStrategy  implements IStrategy{

	@Override
	public void doPublish(int publisherId) {
		// create list for channels
				List<String> channelList = new ArrayList<String>();
				// add channels
				channelList.add("planes");
				channelList.add("cars");
				AbstractEvent event = EventFactory.createEvent(EventType.TypeB, publisherId, new EventMessage("jet" , "tesla"));
				//Output (8) Channel x has event y from publisher x
				System.out.println("Channel planes has event " + event.hashCode() + " from publisher " + publisherId ); 
				System.out.println("Channel cars has event " + event.hashCode() + " from publisher " + publisherId ); 
				Access getter = new Access();
				ChannelEventDispatcher ced = getter.getced();
				ced.postEvent(event, channelList) ;
				
	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		//Output (8) Channel x has event y from publisher x
		System.out.println("Channel planes has event " + event.hashCode() + " from publisher " + publisherId ); 
		System.out.println("Channel cars has event " + event.hashCode() + " from publisher " + publisherId ); 
		List<String> channelList = new ArrayList<String>();
		// add channels
		channelList.add("planes");
		channelList.add("cars");
		Access getter = new Access();
		ChannelEventDispatcher ced = getter.getced();
		ced.postEvent(event, channelList) ;
		
		
	}

}
