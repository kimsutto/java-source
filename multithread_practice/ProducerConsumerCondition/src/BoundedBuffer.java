import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// BoundedBuffer (Condition Variable)
public class BoundedBuffer {
	
	private final Lock mutex = new ReentrantLock();
    private final Condition notFull = mutex.newCondition();
    private final Condition notEmpty = mutex.newCondition();

	private volatile String[] buffer;
	private volatile int tail = 0;
	private volatile int head = 0;
	private volatile int count = 0;

	public BoundedBuffer(int size) {
		buffer = new String[size];
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
		mutex.lock(); // lock
		//System.out.println(Thread.currentThread().getName() + " acquire.. # of empty available=" + empty.availablePermits());
		while (count == buffer.length) {
			System.out.println("Full buffer: count=" + count + " " + Thread.currentThread().getName() + " put waiting..");
			long time = System.currentTimeMillis();
			notFull.await(); // wait until notFull condition
			long blockTime = System.currentTimeMillis() - time;
			System.out.println(Thread.currentThread().getName() + " is ready for use - [ " + blockTime + " ms ] ");
		}		
		System.out.println(Thread.currentThread().getName() + ": producing>>>>>>>>>>>>>>>>>>>> " + data);
		buffer[tail] = data;
		tail = (tail + 1) % buffer.length;		
		count =count+1;
		notEmpty.signal(); // signal notEmpty available
		mutex.unlock(); // unlock
	}
	
	// Consumer take
	public String take() throws InterruptedException {
		mutex.lock(); // lock
		try {
			while (count == 0) {
				System.out.println("\t\tEmpty buffer: count=" + count + " " + Thread.currentThread().getName() + " take waiting..");
				long time = System.currentTimeMillis();
				notEmpty.await(); // wait until notEmpty condition
				long blockTime = System.currentTimeMillis() - time;
				System.out.println("\t\t" + Thread.currentThread().getName() + " is ready for use - [ " + blockTime + " ms ] ");
			}
			
			String data = buffer[head];
			head = (head + 1) % buffer.length;		
			System.out.println("\t\t" + Thread.currentThread().getName() + ": consuming>>>>>>>>>>>>>>>>>>>> " + data);

			notFull.signal(); // signal notFull available
			//System.out.println("\t\t" + Thread.currentThread().getName() + " release.. # of empty available=" + empty.availablePermits() + "\n");
			return data;			
		} finally {
			mutex.unlock();
		}
	}
}
