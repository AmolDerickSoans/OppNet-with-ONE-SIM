package routing;

import java.util.HashMap;

import core.Connection;
import core.DTNHost;
import core.Settings;

public class SprayAndWaitUtilityRouter extends SprayAndWaitRouter{
     
    protected HashMap<Integer,Integer> utilities;
    public SprayAndWaitUtilityRouter(Settings s) {
        super(s);
        utilities = new HashMap<Integer,Integer>();
        
    }

    public SprayAndWaitUtilityRouter(SprayAndWaitRouter r){
        super(r);
        utilities = new HashMap<Integer,Integer>();
    }

    @Override
    public SprayAndWaitUtilityRouter replicate(){
        return new SprayAndWaitUtilityRouter(this);
    }
    
    public void changedConncetion(Connection con){
        super.changedConnection(con);

        if(con.isUp()){
            //Increase the number of contacts with the other node
            int nContacts = 0;
            DTNHost otherNode  = con.getOtherNode(getHost());
            int otherAddress = otherNode.getAddress();

            if(utilities.containsKey(otherAddress)){
                //Already had some contact (s) with this node 
                //Get the current utility vakue for the node
                nContacts = utilities.get(otherAddress);

            }

            //Update the hash table with the new utility value for the concerned node
            utilities.put(otherAddress,nContacts +1);
        }
    }

}
