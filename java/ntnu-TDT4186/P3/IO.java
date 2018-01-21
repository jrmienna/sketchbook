
/**
 * The IO device of the simulated system.
 */
public class IO implements Constants {

    /** Average IO duration */
    private long avgTime;
    /** The active process performing IO */
    private Process process;
    /** Processes waiting to perform IO */
    private Queue queue;
    /** Reference to GUI */
    private Gui gui;
    /** Reference to statistics collector */
    private Statistics statistics;

    /**
     * Creates a new IO entity
     * @param avgTime       Average IO duration.
     * @param gui           GUI interface.
     * @param queue         IO queue.
     * @param statistics    Statistics collector.
     */
    public IO(long avgTime, Queue queue, Gui gui, Statistics statistics) {
        this.gui = gui;
        this.queue = queue;
        this.avgTime = avgTime;
        this.statistics = statistics;
    }
    
    /**
     * Adds a process to the I/O queue, and initiates an I/O operation
     * if the device is free.
     * @param requestingProcess The process to be added to the I/O queue.
     * @param clock             The time of the request.
     * @return                  The event ending the I/O operation, or null
     *                          if no operation was initiated.
     */
    public Event addIoRequest(Process requestingProcess, long clock) {

        // Add the process to the I/O queue
        queue.insert(requestingProcess);
        requestingProcess.calculateTimeToNextIoOperation();

        // Check if a new I/O operation can be started
        return startIoOperation(clock);
    }

    /**
     * Starts a new I/O operation if the I/O device is free and there are
     * processes waiting to perform I/O.
     * @param clock     The global time.
     * @return          An event describing the end of the I/O operation that was started,
     *                  or null	if no operation was initiated.
     */
    public Event startIoOperation(long clock) {

        if (process != null) return null;   // cuz another process is doing IO

        if (queue.isEmpty()) return null;   // cuz no processes waiting for IO

        // Let the first process in queue start IO
        process = (Process) queue.removeNext();
        process.enteredIo(clock);
        gui.setIoActive(process);

        // Update statistics
        statistics.nofProcessedIoOperations++;

        // Calculate duration of IO operation
        int ioTime = 1 + (int) (2 * Math.random() * avgTime);

        return new Event(END_IO, clock + ioTime);
    }

    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed    The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {

        statistics.ioQueueLengthTime += queue.getQueueLength() * timePassed;

        if(queue.getQueueLength() > statistics.ioQueueLargestLength) {
            statistics.ioQueueLargestLength = queue.getQueueLength();
        }
    }

    /**
     * Removes the process currently doing I/O from the I/O device.
     * @return  The process that was doing I/O, or null if no process was doing I/O.
     */
    public Process removeActiveProcess() {

        Process p = process;

        process = null;

        gui.setIoActive(null);

        return p;
    }
}
