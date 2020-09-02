
public class Consumer extends Worker {
	private final String name;

	public Consumer(String name, BoundedBuffer data) {
		super(data);
		this.name = name;
	}

	public String getName() { // get the worker name
		return this.name;
	}
	
	@Override
	public void run() { // run() method override
		while (isRunning()) {
			try {
				String str = data.take(); // take data
				setCurrentItem(str); // set current item
				//System.out.println("\t\t" + Thread.currentThread().getName() + ": " + Thread.currentThread().getState() + " " + str);
				Thread.sleep(rand.nextInt(3000)); 
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}