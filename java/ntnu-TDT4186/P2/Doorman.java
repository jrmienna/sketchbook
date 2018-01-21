/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread implements Constants {

	private CustomerQueue queue;
	private Gui gui;
	private boolean working;

	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) {
		this.queue = queue;
		this.gui = gui;
		this.working = false;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		working = true;
		start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		working = false;
	}

	public void run() {
		int rand;
		int max = MAX_DOORMAN_SLEEP;
		int min = MIN_DOORMAN_SLEEP;
		while (working) {
			rand = min + (int) (Math.random() * (max - min + 1));

            gui.println("Doorman queues new customer");
            queue.enqueueCustomer(new Customer());

            try {
                sleep(Globals.doormanSleep);
                //sleep(rand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
}
