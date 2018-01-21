/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread implements Constants {

    private CustomerQueue queue;
    private Gui gui;
    private int pos;
    private boolean working;

	/**
	 * Creates a new barber.
	 * @param queue		The customer queue shared between all threads.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) {
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
        this.working = false;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
        working = true;
        start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
        working = false;
	}

    public void run() {
        while (working) {
            Customer next = queue.dequeueCustomer();

            gui.println("Barber " + pos + " is barbering...");
            gui.fillBarberChair(pos, next);
            barberWork();
            gui.println("Barber " + pos + " is done barbering");
            gui.emptyBarberChair(pos);

            gui.println("Barber " + pos + " is daydreaming...");
            gui.barberIsSleeping(pos);
            barberSleep();
            gui.println("Barber " + pos + " is done daydreaming");
            gui.barberIsAwake(pos);
        }
    }

    private void barberWork() {
        int maxWork = MAX_BARBER_WORK;
        int minWork = MIN_BARBER_WORK;
        int rand = minWork + (int) (Math.random() * (maxWork - minWork + 1));
        try {
            sleep(Globals.barberWork);
            //sleep(rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void barberSleep() {
        int maxSleep = MAX_BARBER_SLEEP;
        int minSleep = MIN_BARBER_SLEEP;
        int rand = minSleep + (int) (Math.random() * (maxSleep - minSleep + 1));
        try {
            sleep(Globals.barberSleep);
            //sleep(rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

