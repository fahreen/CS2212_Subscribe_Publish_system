package orchestration;


 /**
  * @author Fahreen Bushra
  *  
  * Base Interface for Publisher and Subscriber classes
  */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import events.AbstractEvent;
import events.EventFactory;
import events.EventMessage;
import events.EventType;
import pubSubServer.Access;
import pubSubServer.ChannelAccessControl;
import publishers.AbstractPublisher;
import publishers.PublisherFactory;
import publishers.PublisherType;
import states.subscriber.StateName;
import strategies.publisher.StrategyName;
import subscribers.AbstractSubscriber;
import subscribers.SubscriberFactory;
import subscribers.SubscriberType;

public class Orchestration extends PubSubEntity{

	public static void main(String[] args) {

		List<AbstractPublisher> listOfPublishers = new ArrayList<>();   //create empty list of publishers
		List<AbstractSubscriber> listOfSubscribers = new ArrayList<>(); //create empty list of subscribers
		Orchestration testHarness = new Orchestration();				//create new Orchestration object
		
		try {
			System.out.println("CREATE PUBLISHERS:");
			listOfPublishers = testHarness.createPublishers();			    //get list of publishers from text file
			System.out.println("CREATE SUBSCRIBERS:");
			listOfSubscribers = testHarness.createSubscribers(); 			//get list of subscribers from text file		
			
			// ChannelPoolManager.getInstance is protected thus a class is used to get access to the instance
			// reads channels.chl and add channels  pub sub server
			System.out.println("CREATE CHANNELS:");
			Access getter = new Access();
			getter.getcpm();
			
			// read path .txt
			BufferedReader path = new BufferedReader(new FileReader(new File("path.txt"))); 
			int num = 1;
			while(path.ready()) { 
				String pathLine =path.readLine(); // read a line from the file
				System.out.println("");
				System.out.println("BEGIN  COMMAND" + num + " : " +pathLine);
				num++;
				String[] pathArray = pathLine.split(" ");
				String command = pathArray[0];
				if (command .equals("PUB")){
					if (pathArray.length > 2)
						testHarness.pub2(Integer.parseInt(pathArray[1]), EventType.valueOf(pathArray[2]),pathArray[3], pathArray[4], listOfPublishers);
					else
						testHarness.pub1(Integer.parseInt(pathArray[1]),listOfPublishers);}
				 if (command .equals("SUB")) {
					testHarness.sub(Integer.parseInt(pathArray[1]), pathArray[2], listOfSubscribers);}	
				 if (command .equals("BLOCK")) {
					testHarness.block(Integer.parseInt(pathArray[1]), pathArray[2], listOfSubscribers);}
				 if (command .equals("UNBLOCK")) {
					testHarness.unBlock(Integer.parseInt(pathArray[1]), pathArray[2], listOfSubscribers);
				}		
			}
			path.close();
			}
			catch(IOException ioe) {
				System.out.println("failed to read file");
		}
	}
			private void pub1(int publisherId, List<AbstractPublisher> listOfPublishers ) {
			for(AbstractPublisher publisher : listOfPublishers) 
				if (publisher.hashCode() ==  publisherId)
						publisher.publish();
		}
			
// publish by all publishers an event		
		private void pub2(int publisherId,  EventType e,  String header, String payload, List<AbstractPublisher> listOfPublishers  ) {
			//createEvent  
			AbstractEvent event = EventFactory.createEvent(e ,publisherId, new EventMessage(payload, "this is supposed to be a body")); //(EventType eventType, int eventPublisherId, EventMessage payload)
			// publish event
			for(AbstractPublisher publisher : listOfPublishers) {
				if (publisher.hashCode() ==  publisherId)
						publisher.publish(event);			
		}}
			
		private void sub(int subscriberID , String channelName, List<AbstractSubscriber> listOfSubscribers ) {
			for(AbstractSubscriber subscriber : listOfSubscribers) {
				if (subscriber.hashCode() == subscriberID)
					subscriber.subscribe(channelName);
			}
			}
		
		
		
		private void block(int subscriberID, String channelName , List<AbstractSubscriber> listOfSubscribers ) {
			Access getter = new Access();
			ChannelAccessControl cac = getter.getcac();
			for(AbstractSubscriber subscriber : listOfSubscribers) {
				if (subscriber.hashCode() == subscriberID )
				cac.blockSubcriber(subscriber, channelName);
		}
		}
		
		
		private void unBlock(int subscriberID, String channelName , List<AbstractSubscriber> listOfSubscribers ) {
			Access getter = new Access();
			ChannelAccessControl cac = getter.getcac();;
			for(AbstractSubscriber subscriber : listOfSubscribers) {
				if (subscriber.hashCode() == subscriberID )
					cac.unBlockSubscriber(subscriber, channelName);
		}}
		
		
	
	
	
	// return List<AbstractPublisher> from Strategies.str
	private List<AbstractPublisher> createPublishers() throws IOException{
		
		List<AbstractPublisher> listOfPublishers = new ArrayList<>(); 	// create empty List<AbstractPublisher>
		AbstractPublisher newPub; 										// create new AbstractPublisher (newPub)
		BufferedReader StrategyBufferedReader = new BufferedReader(new FileReader(new File("Strategies.str"))); // <publisher-ID, strategy-ID>. 
		while(StrategyBufferedReader.ready()) { // while a line exists
			String PublisherConfigLine = StrategyBufferedReader.readLine(); // read a line from the file
			String[] PublisherConfigArray = PublisherConfigLine.split("\t"); //split the line to [int(type), int(strategy)]
			int[] PublisherConfigIntArray = new int[2]; 					// create int array
			
			for(int i = 0; i < PublisherConfigArray.length; i++)
				PublisherConfigIntArray[i] = Integer.parseInt(PublisherConfigArray[i]); // convert both elements of string array to int, then add to intArray
			newPub = PublisherFactory.createPublisher(PublisherType.values()[PublisherConfigIntArray[0]],StrategyName.values()[PublisherConfigIntArray[1]]); // create a concrete publisher using string 
			listOfPublishers.add(newPub);  // add concrete publisher to List<AbstractPublisher>
		}
		StrategyBufferedReader.close();  // close file
		return listOfPublishers;		// return list of publishers
	}
	
	
	
	// return List<AbstractSubscriber>
	private List<AbstractSubscriber> createSubscribers() throws IOException{
		List<AbstractSubscriber> listOfSubscribers = new ArrayList<>();		// create empty List<AbstractSubscriber>
		AbstractSubscriber newSub;											// create new subscriber
		BufferedReader StateBufferedReader = new BufferedReader(new FileReader(new File("States.sts")));  //<subscriber-ID, state-ID>. 
		while(StateBufferedReader.ready()) {
			String StateConfigLine = StateBufferedReader.readLine();
			String[] StateConfigArray = StateConfigLine.split("\t");
			int[] StateConfigIntArray = new int[2];
			for(int i = 0; i < StateConfigArray.length; i++)
				StateConfigIntArray[i] = Integer.parseInt(StateConfigArray[i]);
			newSub = SubscriberFactory.createSubscriber(SubscriberType.values()[StateConfigIntArray[0]], StateName.values()[StateConfigIntArray[1]]);
			listOfSubscribers.add(newSub);
		}
		StateBufferedReader.close();
		return listOfSubscribers;
	}
	

	
	
}
