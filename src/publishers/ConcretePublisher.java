package publishers;

import events.AbstractEvent;
import strategies.publisher.IStrategy;
import strategies.publisher.StrategyFactory;


/**
 * @author kkontog, ktsiouni, mgrigori
 * 
 * the WeatherPublisher class is an example of a ConcretePublisher 
 * implementing the IPublisher interface. Of course the publish 
 * methods could have far more interesting logics
 */
public class ConcretePublisher extends AbstractPublisher {

	
	/**
	 * @param concreteStrategy attaches a concreteStrategy generated from the {@link StrategyFactory#createStrategy(strategies.publisher.StrategyName)}
	 * method
	 */
	protected ConcretePublisher(IStrategy concreteStrategy) {
		
		this.publishingStrategy = concreteStrategy;
		
	}

	/* (non-Javadoc)
	 * @see publishers.IPublisher#publish(events.AbstractEvent)
	 */
	@Override
	public void publish(AbstractEvent event) {
		//Output (7) Publisher x publishes event y
		System.out.println("Publisher " + this.hashCode() + " publishes specified event "+ event.hashCode() );
		publishingStrategy.doPublish(event, this.hashCode());
		 
	}

	
	/* (non-Javadoc)
	 * @see publishers.IPublisher#publish()
	 */
	@Override
	public void publish() {
		System.out.println("Publisher " + this.hashCode() + " publishes an event " );

		publishingStrategy.doPublish(this.hashCode());
	}

}
