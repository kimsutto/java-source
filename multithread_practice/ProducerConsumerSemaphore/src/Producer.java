// Producer (Semaphore)
public class Producer extends Worker {
	private final String name;
	private static volatile int count = 0; // global counter (shared resource among producer threads)

	public Producer(String name, BoundedBuffer data) {
		super(data);
		this.name = name;
	}

	@Override
	public String getName() { // get the worker name
		return this.name;
	}
	
	@Override
	public void run() { // run() method override
		int i = 0;
		while (isRunning()) {
			try {
				//int count = (int) (Math.random() * 50) + 20;
				String str = getName() + " cycle=" + i + " gcount=" + count++;
				i++;
				data.put(str); // put data
				setCurrentItem(str); // set current item
				Thread.sleep(rand.nextInt(1000)); // random sleep
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
