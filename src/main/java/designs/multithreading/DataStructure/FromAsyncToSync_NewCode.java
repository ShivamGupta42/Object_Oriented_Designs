package designs.multithreading.DataStructure;

class ExecutorNew extends Executor {

	// Basically we have to make this method blocking
	@Override
	public void asynchronousExecution(Callback callback) throws Exception {
		Object signal = new Object();

		boolean[] isDone = new boolean[1];

		Callback localCallback = new Callback() {
			@Override
			public void done() {
				callback.done();
				isDone[0] = true;
				synchronized (signal) {
					signal.notify();
				}
			}
		};
		super.asynchronousExecution(localCallback);

		synchronized (signal) {
			while (!isDone[0]) {
				signal.wait();
			}
		}
	}
}

public class FromAsyncToSync_NewCode {

	public static void main(String[] args) throws Exception {
		ExecutorNew exec = new ExecutorNew();
		exec.asynchronousExecution(() -> System.out.println("done"));
		System.out.println("Main thread exiting");

	}

}
