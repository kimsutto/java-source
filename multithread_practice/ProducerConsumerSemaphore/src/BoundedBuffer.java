import java.util.concurrent.Semaphore;
// BoundedBuffer (Semaphore)
public class BoundedBuffer {
	
	private Semaphore mutex = null;
	private Semaphore empty = null;
	private Semaphore full = null;

	private String[] buffer = null;
	private int tail = 0, head = 0;

	public BoundedBuffer(int size) {
		buffer = new String[size];
		mutex = new Semaphore(1); // mutex
		empty = new Semaphore(size); // empty semaphore 5
		full = new Semaphore(0); // full semaphore 0
	}
	
	public String[] getBuffer() {
		return this.buffer;
	}
	
	public int getHead() {
		return this.head;
	}
	
	public int getTail() {
		return this.tail;
	}
	
	// Producer put
	public void put(String data) throws InterruptedException {
		//System.out.println(Thread.currentThread().getName() + " acquire.. # of empty available=" + empty.availablePermits());
		if (empty.availablePermits() == 0) {
			System.out.println("FULL BUFFER: # of empty available=" + empty.availablePermits() + " # of full available=" + full.availablePermits() + " " + Thread.currentThread().getName() + " put waiting..");
		}
		long time = System.currentTimeMillis();
		System.out.println("PUT # of empty available=" + empty.availablePermits() + " # of full available=" + full.availablePermits());
		empty.acquire(); // decrease empty available
		long blockTime = System.currentTimeMillis() - time;
		System.out.println(Thread.currentThread().getName() + " is ready for use - [ " + blockTime + " ms ] ");
		
		mutex.acquire(); // lock
		System.out.println(Thread.currentThread().getName() + ": producing>>>>>>>>>>>>>>>>>>>> " + data);
		buffer[tail] = data;
		tail = (tail + 1) % buffer.length;		
		mutex.release(); // unlock

		full.release(); // increase full available
		//System.out.println(Thread.currentThread().getName() + " release.. # of full available=" + full.availablePermits() + "\n");
	}
	
	// Consumer take
	public String take() throws InterruptedException {
		//System.out.println("\t\t" + Thread.currentThread().getName() + " acquire.. # of full available =" + full.availablePermits());	
		if (full.availablePermits() == 0) {
			System.out.println("\t\tEMPTY BUFFER: # of full available=" + full.availablePermits() + " " + Thread.currentThread().getName() + " take waiting..");
		}
		long time = System.currentTimeMillis();
		full.acquire(); // decrease full available
		long blockTime = System.currentTimeMillis() - time;
		System.out.println("\t\t" + Thread.currentThread().getName() + " is ready for use - [ " + blockTime + " ms ] ");
		
		mutex.acquire(); // lock
		String data = buffer[head];
		head = (head + 1) % buffer.length;		
		System.out.println("\t\t" + Thread.currentThread().getName() + ": consuming>>>>>>>>>>>>>>>>>>>> " + data);
		mutex.release(); // unlock			
		
		empty.release(); // increase empty available
		//System.out.println("\t\t" + Thread.currentThread().getName() + " release.. # of empty available=" + empty.availablePermits() + "\n");
		return data;			
	}
}
