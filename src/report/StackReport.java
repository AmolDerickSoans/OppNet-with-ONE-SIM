package report;

import java.util.List;
import core.DTNHost;
// import core.Settings;
import core.SimClock;
import core.UpdateListener;
import core.SimScenario;
import routing.MessageRouter;
// import gui.playfield.DTNSimGUI;
// import java.util.ArrayList;
// import java.util.Random;

public class StackReport extends Report implements UpdateListener{

	private int interval;
	// private double lastRecord = Double.MIN_VALUE;
	protected SimScenario scen;

	public MessageRouter roter;
	// public static final String BUFFER_REPORT_INTERVAL = "5";
	public static final int DEFAULT_BUFFER_REPORT_INTERVAL = 5;
	public static final int endtime = 20000;
	String output;



	public StackReport() {
		super();

		if (interval < 0) { /* not found or invalid value -> use default */
			interval = DEFAULT_BUFFER_REPORT_INTERVAL;
		}
	}

	public void updated(List<DTNHost> hosts) {
		if(SimClock.getTime()==endtime-1)
		{
			write(output);
		}
	}

	public void getsnd()
	{
		// this.roter = new MessageRouter();
		// String x= retfrom();
		// output="from: "+x+" to:"+ y;
	}


	// private void trustcalc(List<DTNHost> hosts)
	// {
	// 	// this.scen = SimScenario.getInstance();
	// 	// System.out.println(scen.selfishThreshold);
	// 	if(scen.selfishBehavior){
	// 		Random r = new Random();
	// 		int randomThreshold=r.nextInt(29)+40;
	// 		scen.setAllSelfishDegree(randomThreshold);//[1 0 0 1 1]
	// 													//[0 0 0 0]
	// 		int j=0;
	// 		for(DTNHost h: hosts)
	// 		{
	// 			if(h.selfishdegree==0)
	// 			{
	// 				int preval=trustcount.get(j);
	// 				trustcount.set(j, ++preval);//0 means non selfish and trust increases
	// 			}
	// 			else if(h.selfishdegree==1)
	// 			{
	// 				int preval=trustcount.get(j);
	// 				trustcount.set(j, --preval);
	// 			}
	// 			j++;
	// 		}
	// 	}
	// }

	// private void printtrusttable(int size)
	// {	
	// 	String output;
	// 	for(int i=1;i<=size;i++)
	// 	{
	// 		output=String.valueOf(trustcount.get(i-1));
	// 		write(output);
	// 	}
	// }
	// private void initrusttable(int size)
	// {	
	// 	System.out.println("TABLE INITIALISED");
	// 	for(int i=0;i<size;i++)
	// 	{
	// 		trustcount.add(0);
	// 	}
	// }



}