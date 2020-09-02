public class ProducerConsumerTask {

	BoundedBuffer data = null;
	Producer[] producers = null;
	Consumer[] consumers = null;
	int numProducer;
	int numConsumer;
	int numBuffer;
	
	public ProducerConsumerTask(int numProducer, int numConsumer, int numBuffer) {
		this.numProducer = numProducer;
		this.numConsumer = numConsumer;
		this.numBuffer = numBuffer;

		data = new BoundedBuffer(this.numBuffer);
		producers = new Producer[this.numProducer];
		for (int i = 0; i < this.numProducer; i++) {
			producers[i] = new Producer("Producer"+(i+1), data);
			new Thread(producers[i]).start();
		}
		consumers = new Consumer[numConsumer];
		for (int i = 0; i < this.numConsumer; i++) {
			consumers[i] = new Consumer("Consumer"+(i+1), data);
			new Thread(consumers[i]).start();
		}
	}
	
	public void stop() {
		for (int i = 0; i < this.numProducer; i++) {
			producers[i].stop();
		}
		for (int i = 0; i < this.numConsumer; i++) {
			consumers[i].stop();
		}
	}	
	
}
