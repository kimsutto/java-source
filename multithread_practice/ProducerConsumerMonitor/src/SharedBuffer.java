// SharedBuffer (Monitor)
public class SharedBuffer {

	private volatile String[] buffer;
	private volatile int tail = 0;
	private volatile int head = 0;
	private volatile int count = 0;
	
	public SharedBuffer(int size) {
		this.buffer = new String[size];
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

	public int getCount() {
		return this.count;
	}
	
	// Producer put
	public synchronized void put(String data) throws InterruptedException {
		while (count == buffer.length) {
			System.out.println("Full buffer: count=" + count + " " + Thread.currentThread().getName() + " put waiting..");
			long time = System.currentTimeMillis();
			wait(); // wait
			long blockTime = System.currentTimeMillis() - time;
			System.out.println(Thread.currentThread().getName() + " is ready for use - [ " + blockTime + " ms ] ");
		}
		System.out.println(Thread.currentThread().getName() + ": producing " + data);
		buffer[tail] = data;
		tail = (tail + 1) % buffer.length;
		count++;
		notifyAll(); // signal
	}
	
	// Consumer take
	public synchronized String take() throws InterruptedException {
		while (count == 0) {
			System.out.println("\t\tEmpty buffer: count=" + count + " " + Thread.currentThread().getName() + " take waiting..");
			long time = System.currentTimeMillis();
			wait(); // wait
			long blockTime = System.currentTimeMillis() - time;
			System.out.println("\t\t" + Thread.currentThread().getName() + " is ready for use - [ " + blockTime + " ms ] ");
		}
		String data = buffer[head];
		head = (head + 1) % buffer.length;
		count--;
		System.out.println("\t\t" + Thread.currentThread().getName() + ": consuming " + data);
		notifyAll(); // signal
		return data;
	}
}
