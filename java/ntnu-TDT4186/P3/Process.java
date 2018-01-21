import java.awt.*;
import java.util.*;

/**
 * This class contains data associated with processes,
 * and methods for manipulating this data as well as
 * methods for displaying a process in the GUI.
 */
public class Process implements Constants
{
	/** The ID of the next process to be created */
	private static long nextProcessId = 1;
	/** The font used by all processes */
	private static Font font = new Font("Arial", Font.PLAIN, 10);
	/** The ID of this process */
	private long processId;
	/** The color of this process */
	private Color color;
	/** The amount of memory needed by this process */
    private long memoryNeeded;
	/** The amount of cpu time still needed by this process */
    private long cpuTimeNeeded;
	/** The average time between the need for I/O operations for this process */
    private long avgIoInterval;
	/** The time left until the next time this process needs I/O */
    private long timeToNextIoOperation = 0;

	/** The time that this process has spent waiting in the memory queue */
	private long timeSpentWaitingForMemory = 0;
	/** The time that this process has spent waiting in the CPU queue */
	private long timeSpentInReadyQueue = 0;
	/** The time that this process has spent processing */
    private long timeSpentInCpu = 0;
	/** The time that this process has spent waiting in the I/O queue */
    private long timeSpentWaitingForIo = 0;
	/** The time that this process has spent performing I/O */
	private long timeSpentInIo = 0;

	/** The number of times that this process has been placed in the CPU queue */
	private long nofTimesInReadyQueue = 0;
	/** The number of times that this process has been placed in the I/O queue */
	private long nofTimesInIoQueue = 0;

	/** The global time of the last event involving this process */
	private long timeOfLastEvent;

	/**
	 * Creates a new process with given parameters. Other parameters are randomly
	 * determined.
	 * @param memorySize	The size of the memory unit.
	 * @param creationTime	The global time when this process is created.
	 */
	public Process(long memorySize, long creationTime) {
		// Memory need varies from 100 kB to 25% of memory size
		memoryNeeded = 100 + (long)(Math.random()*(memorySize/4-100));
		// CPU time needed varies from 100 to 10000 milliseconds
		cpuTimeNeeded = 100 + (long)(Math.random()*9900);
		// Average interval between I/O requests varies from 1% to 25% of CPU time needed
		avgIoInterval = (1 + (long)(Math.random()*25))*cpuTimeNeeded/100;
		// The first and latest event involving this process is its creation
		timeOfLastEvent = creationTime;
		// Assign a process ID
		processId = nextProcessId++;
		// Assign a pseudo-random color used by the GUI
		int red = 64+(int)((processId*101)%128);
		int green = 64+(int)((processId*47)%128);
		int blue = 64+(int)((processId*53)%128);
		color = new Color(red, green, blue);
	}

	/**
	 * Draws this process as a colored box with a process ID inside.
	 * @param g	The graphics context.
	 * @param x	The leftmost x-coordinate of the box.
	 * @param y	The topmost y-coordinate of the box.
	 * @param w	The width of the box.
	 * @param h	The height of the box.
	 */
	public void draw(Graphics g, int x, int y, int w, int h) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.drawRect(x, y, w, h);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		g.drawString(""+processId, x+w/2-fm.stringWidth(""+processId)/2, y+h/2+fm.getHeight()/2);
	}

    /**
	 * This method is called when the process leaves the memory queue (and
	 * enters the cpu queue).
     * @param clock The time when the process leaves the memory queue.
     */
    public void leftMemoryQueue(long clock) {
		  timeSpentWaitingForMemory += clock - timeOfLastEvent;
		  timeOfLastEvent = clock;
    }

    /**
     * Returns the amount of memory needed by this process.
     * @return	The amount of memory needed by this process.
     */
    public long getMemoryNeeded() {
        return memoryNeeded;
    }

	/**
	 * Updates the statistics collected by the given Statistic object, adding
	 * data collected by this process. This method is called when the process
	 * leaves the system.
	 * @param statistics	The Statistics object to be updated.
	 */
	public void updateStatistics(Statistics statistics) {
		statistics.totalTimeSpentWaitingForMemory += timeSpentWaitingForMemory;
		statistics.totalTimeSpentInReadyQueue += timeSpentInReadyQueue;
		statistics.totalTimeSpentInCpu += timeSpentInCpu;
		statistics.totalTimeSpentWaitingForIo += timeSpentWaitingForIo;
		statistics.totalTimeSpentInIo += timeSpentInIo;

		statistics.totalNofTimesInReadyQueue += nofTimesInReadyQueue;
		statistics.totalNofTimesInIoQueue += nofTimesInIoQueue;

		statistics.nofCompletedProcesses++;
	}

    // Add more methods as needed

	/**
	 * This method is called when the process is active and a discrete
	 * amount of time has passed.
	 * @param timePassed	The amount of time that has passed since the last time this method was invoked.
	 */
	public void cpuTimePassed(long timePassed) {
		cpuTimeNeeded -= timePassed;
		timeToNextIoOperation -= timePassed;
	}

    /**
	 * This method is called when the process leaves the CPU queue (and
	 * enters the CPU).
     * @param clock The time when the process enters the CPU.
     */
    public void enteredCpu(long clock) {
		nofTimesInReadyQueue++;
		timeSpentInReadyQueue += clock - timeOfLastEvent;
		timeOfLastEvent = clock;
	}

    /**
	 * This method is called when the process leaves the CPU.
     * @param clock The time when the process leaves the CPU.
     */
	public void leftCpu(long clock) {
		timeSpentInCpu += clock-timeOfLastEvent;
		timeOfLastEvent = clock;
	}

    /**
	 * This method is called when the process leaves the I/O queue (and
	 * enters the I/O device).
     * @param clock The time when the process enters the I/O device.
     */
    public void enteredIo(long clock) {
		nofTimesInIoQueue++;
		timeSpentWaitingForIo += clock - timeOfLastEvent;
		timeOfLastEvent = clock;
    }

    /**
	 * This method is called when the process leaves the I/O device.
     * @param clock The time when the process leaves the I/O device.
     */
    public void leftIo(long clock) {
		timeSpentInIo += clock - timeOfLastEvent;
		timeOfLastEvent = clock;
    }

    /**
	 * Randomly determines how long (in execution time) it will be until the
	 * next time this process needs to perform I/O.
     */
    public void calculateTimeToNextIoOperation() {
		timeToNextIoOperation = 1 + (long)(2*Math.random()*avgIoInterval);
    }

	/**
	 * Finds out what the next event for this process will be, based on how
	 * much execution time remains, what the RR time quant is, and how much
	 * execution time is left until the next time the process needs I/O.
	 * @return	An Event object describing the next event involving this process.
	 */
	public Event getNextEvent(long clock, long maxCpuTime) {

        int type = 0;
        long time = clock;

        if ((cpuTimeNeeded < maxCpuTime && cpuTimeNeeded >= timeToNextIoOperation) ||
            (cpuTimeNeeded > maxCpuTime && timeToNextIoOperation < maxCpuTime)) {
            type = IO_REQUEST;
            time += timeToNextIoOperation;
        }
        if (cpuTimeNeeded < maxCpuTime && cpuTimeNeeded < timeToNextIoOperation) {
            type = END_PROCESS;
            time += cpuTimeNeeded;
        }
        if (cpuTimeNeeded > maxCpuTime && timeToNextIoOperation >= maxCpuTime) {
            type = SWITCH_PROCESS;
            time += maxCpuTime;
        }

        return new Event(type, time);
   	}
}