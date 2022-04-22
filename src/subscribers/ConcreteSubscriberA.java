package subscribers;

import events.AbstractEvent;
import pubSubServer.SubscriptionManager;
import states.subscriber.StateFactory;
import states.subscriber.StateName;


/**
 * @author kkontog, ktsiouni, mgrigori
 * an example concrete subscriber
 */
class ConcreteSubscriberA extends AbstractSubscriber {

	protected ConcreteSubscriberA() {
		state = StateFactory.createState(StateName.defaultState);
		
		
	}
	
	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#setState(states.subscriber.StateName)
	 */
	public void setState(StateName stateName) {
		state = StateFactory.createState(stateName);
		
	}
	
	
	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#alert(events.AbstractEvent, java.lang.String)
	 */
	@Override
	public void alert(AbstractEvent event, String channelName) {
		//output(9): Subscriber x receives event y and handles it at state z
		System.out.println("Subscriber " + this.hashCode() + " receives event " + event.hashCode() + " published on channel " + channelName + " and handles it at state: " + this.state  );
		state.handleEvent(event, channelName);
		
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#subscribe(java.lang.String)
	 */
	@Override
	public void subscribe(String channelName) {
		//output(6): “Subscriber x subscribes to channel y”
		System.out.println("Subscriber " + this.hashCode() + " subscribes to " + channelName); 
		SubscriptionManager.getInstance().subscribe(channelName, this);		
		
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#unsubscribe(java.lang.String)
	 */
	@Override
	public void unsubscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);
		
	}
	
	
}
