package report;

/** 
 * 
 * @author	duttaani , nayan
 */
import java.util.List;

import core.DTNHost;
import core.Settings;
import core.SimClock;
import core.UpdateListener;
import core.SimScenario;

import java.util.ArrayList;

import java.util.Random;

public class UpdateArunSirRouterSelfishness extends Report implements UpdateListener{
    /**
	 * Record occupancy every nth second -setting id ({@value}). Defines the
	 * interval how often (seconds) a new snapshot of selfishness update is taken
	 */
	public static final String BUFFER_REPORT_INTERVAL = "5";//"selfishness update Interval"; // enter time to update here
	/** Default value for the snapshot interval */
	public static final int DEFAULT_BUFFER_REPORT_INTERVAL = 5;

	private double lastRecord = Double.MIN_VALUE;
	private int interval;

	/** Scenario of the current simulation */
	protected SimScenario scen;

    /**
	 * Creates a new BufferOccupancyReport instance.
	 */
	public UpdateArunSirRouterSelfishness() {
		super();

		// Settings settings = getSettings();
		// if (settings.contains(BUFFER_REPORT_INTERVAL)) {
		// 	interval = settings.getInt(BUFFER_REPORT_INTERVAL);
		// } else {
		// 	interval = -1; /* not found; use default */
		// }

		if (interval < 0) { /* not found or invalid value -> use default */
			interval = DEFAULT_BUFFER_REPORT_INTERVAL;
		}

		this.scen = SimScenario.getInstance();
	}

    public void updated(List<DTNHost> hosts) {
		if (SimClock.getTime() - lastRecord >= interval) {
			lastRecord = SimClock.getTime();
			printLine(hosts);
		}
	}

	/**
	 * Prints a snapshot of the average buffer occupancy
	 * 
	 * @param hosts The list of hosts in the simulation
	 */
	private void printLine(List<DTNHost> hosts) {

        // Using RANDOM HERE TO DYNAMICALLY CHANGE THE DEGREE
        Random n = new Random();
        int tot_selfish_nodes = n . nextInt ( hosts.size() );
        int count = 0;
		for (DTNHost h : hosts) {

            if(count < tot_selfish_nodes ){
                h.setSelfishBehaviorStatus(true);
                ++count;
            }
            else{
                h.setSelfishBehaviorStatus(false);
            }
			String output = String.valueOf(SimClock.getTime()) + " "  + String.valueOf(h.getAddress()) + " selfishStatus = ";
			// for( ArrayList<Integer> z : h.getTrail() ){
			// 	output += "(" + String.valueOf(z.get(0)) + "," + String.valueOf(z.get(1)) + ")-";
			// }
            output += String.valueOf(h.getSelfishBehaviorStatus());
            // String output = String.valueOf(SimClock.getTime()) + " "  + String.valueOf(h.getAddress()) + " " + String.valueOf(h.getLocation().getX() ) + " " + String.valueOf(h.getLocation().getY() ) + " " + String.valueOf(x) + " " + String.valueOf(y) ;
            write(output);

        } 
    }
}
