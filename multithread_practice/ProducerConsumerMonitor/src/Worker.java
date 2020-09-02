import java.util.Random;

public abstract class Worker implements Runnable {
	public static final int RUNNING = 0;
	public static final int WAITING = 1;
	
	protected Random rand;
	protected final SharedBuffer data;
	protected String currentItem;
	protected int state;
	protected boolean running;
	protected boolean shutdown;
		
	public Worker(SharedBuffer data) { // constructor
		this.data = data;
		this.running = true;
		this.shutdown = false;
		rand = new Random();
	}
	
	public abstract String getName(); // get the worker name

	public synchronized void setCurrentItem(String item) { // set the current item
		this.currentItem = item;
	}
	
	public synchronized String getCurrentItem() { // get the current item
		return this.currentItem;
	}
	
	public synchronized void setState(int state) { // set the worker state
		this.state = state;
	}
	
	public synchronized int getState() { // get the worker state
		return this.state;
	}
	
	public synchronized boolean isRunning() { // return whether or not the worker is running
		if (this.shutdown) {
			this.running = false;
		}
		return this.running;
	}
	
	public synchronized void stop() {
		this.shutdown = true;
	}
	
}
