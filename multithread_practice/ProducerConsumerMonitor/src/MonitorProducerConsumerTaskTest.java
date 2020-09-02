
public class MonitorProducerConsumerTaskTest {
	
	public static void main(String[] args) {		
		// Producer & Consumer (monitor)
		System.out.println("Monitor Producer & Consumer start...");
		
		SharedBuffer data = new SharedBuffer(5);
		Thread[] ths = new Thread[7]; 
		ths[0] = new Thread(new Producer("Producer1", data));
		ths[1] = new Thread(new Producer("Producer2", data));
		ths[2] = new Thread(new Producer("Producer3", data));
		ths[3] = new Thread(new Consumer("Consumer1", data));
		ths[4] = new Thread(new Consumer("Consumer2", data));
		ths[5] = new Thread(new Consumer("Consumer3", data));
		ths[6] = new Thread(new Consumer("Consumer4", data));
		for (Thread t : ths) {
			t.start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Thread t : ths) {
			t.interrupt(); // InterruptedException to run() method to exit
		}

		System.out.println("Monitor Producer & Consumer ended...");
	}
}
