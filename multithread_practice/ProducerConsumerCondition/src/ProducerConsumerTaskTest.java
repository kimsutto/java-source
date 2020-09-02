
public class ProducerConsumerTaskTest {
	
	public static void main(String[] args) {		
		// Producer & Consumer (lock, condition variable)
		System.out.println("Producer & Consumer (lock, condition variable) start...");
		ProducerConsumerTask task = new ProducerConsumerTask(4,3,5);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		task.stop();
		System.out.println("Producer & Consumer (lock, condition variable) ended...");

	}
}
