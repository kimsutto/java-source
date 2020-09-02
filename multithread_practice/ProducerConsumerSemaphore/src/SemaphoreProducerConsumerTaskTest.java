
public class SemaphoreProducerConsumerTaskTest {
	
	public static void main(String[] args) {		
		// Producer & Consumer (mutex, empty semaphore, full semaphore)
		System.out.println("Producer & Consumer (mutex, empty semaphore, full semaphore) start...");
		ProducerConsumerTask task = new ProducerConsumerTask(4,3,5);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		task.stop();
		
		System.out.println("Producer & Consumer (mutex, empty semaphore, full semaphore) ended...");

	}
}
