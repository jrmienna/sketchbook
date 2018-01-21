import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {

	private int queueLength;
	private Gui gui;
	private LinkedList<Customer> queue;

    private int tail;
    private int head;

	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
		this.queueLength = queueLength;
		this.gui = gui;
		this.queue = new LinkedList<Customer>();

        this.tail = 0;
        this.head = 0;
	}

    /**
     *
     * @param customer being appended to the queue.
     * @return true
     */
	public synchronized boolean enqueueCustomer(Customer customer) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.add(customer);
        gui.fillLoungeChair(tail, customer);

        if (tail++ >= queueLength - 1) tail = 0;

        notifyAll();

        return true;
	}

    /**
     * @return Customer - the customer that was removed from the queue.
     */
	public synchronized Customer dequeueCustomer() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Customer c = queue.poll();
		gui.emptyLoungeChair(head);

        if (head++ >= queueLength - 1) head = 0;

        notifyAll();

		return c;
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

    public boolean isFull() {
        return queue.size() >= queueLength;
    }
}
