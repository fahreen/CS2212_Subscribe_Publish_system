package subscribers;

import states.subscriber.StateName;


/**
 * @author kkontog, ktsiouni, mgrigori
 *  
 */
/**
 * @author kkontog, ktsiouni, mgrigori
 * creates new {@link AbstractSubscriber} objects
 * contributes to the State design pattern
 * implements the FactoryMethod design pattern   
 */
public class SubscriberFactory {

	
	/**
	 * creates a new {@link AbstractSubscriber} using an entry from the {@link SubscriberType} enumeration
	 * @param subscriberType a value from the {@link SubscriberType} enumeration specifying the type of Subscriber to be created 
	 * @return the newly created {@link AbstractSubscriber} instance 
	 */

	 
/**
 * @author Fahreen Bushra 
 */
	public static AbstractSubscriber createSubscriber(SubscriberType subscriberType, StateName stateName) {
		
		// CHANGED CSA FROM NULL TO CONCRETE
		AbstractSubscriber CSA ;
		switch (subscriberType) {
			case alpha : 
				CSA = new ConcreteSubscriberA();
				CSA.setState(stateName);
				//output(4) :Subscriber x created 
				//output(5) :Subscriber x is on state y
				System.out.println("Subscriber "+ CSA.hashCode() + " created");
				System.out.println("Subscriber " + CSA.hashCode() + " is on state " + stateName.toString()); 
				System.out.println("" ); 
				return CSA;
				
			default:
				CSA = new ConcreteSubscriberA();
				CSA.setState(stateName);
				//output(4) :Subscriber x created 
				//output(5) :Subscriber x is on state y
				System.out.println("Subscriber "+ CSA.hashCode() + " created");
				System.out.println("Subscriber " + CSA.hashCode() + " is on state " + stateName.toString()); 
				System.out.println("" ); 
				return CSA;
		}
	}
	
}
