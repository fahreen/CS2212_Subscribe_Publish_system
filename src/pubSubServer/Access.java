package pubSubServer;

public class Access {
	

	public ChannelPoolManager getcpm() {
		ChannelPoolManager cpm = ChannelPoolManager.getInstance();
		return cpm;
	}
	public ChannelEventDispatcher getced() {
		 ChannelEventDispatcher ced = ChannelEventDispatcher.getInstance();
		 return ced;
	}
	
	public ChannelAccessControl getcac() {
		ChannelAccessControl cac = ChannelAccessControl.getInstance();
		 return cac;
	}
	
	}
