/*
Spray and Wait routing protocol termed as, Spray and Wait
with Utility (SnWU). As the name suggests, we wish to augment the classical SnW
with a notion of utility. In SnWU, the utility U(i, j) of a node i for any other node
j is given by the total number of times these two nodes came in contact. Similar to
SnW, in SnWU, too, a message is not replicated if its current number of copies is less
than two. Additionally, any node i replicates a message to another node j only if the
utility of the latter node, U(j, d), is greater than that of the former, U(i, d), where d
is the destination of the message, i = j = d.
*/
package routing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.html.ListView;

import core.Connection;
import core.DTNHost;
import core.Message;
import util.Tuple;
import core.*;

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
                //Get the current utility value for the node
                nContacts = utilities.get(otherAddress);

            }

            //Update the hash table with the new utility value for the concerned node
            utilities.put(otherAddress,nContacts +1);
        }
    }

    @Override
    public void update(){
        super.update();

        if(!canStartTransfer() || isTransferring()){
            return;
            //nothing to transfer or is currently transferring
        }

        //try messages that could be delivered to final recipient
        if(exchangeDeliverableMessages() != null){
            return;
        }

        tryOtherMessages();
    }

    @Override
    protected List<Message> getMessagesWithCopiesLeft(){
        
        //This is required to override the finctionality of the superclass
        //where a message is replicated just if number of copies > 1
        return new ArrayList<Message>();
        
    }

    protected Tuple<Message,Connection> tryOtherMessages(){
        List<Tuple<Message,Connection>>messages = new ArrayList<Tuple<Message,Connection>>();

        Collection<Message> msgCollection = getMessageCollection();

        /**
         * *For all connected hosts collect all messages that have a higher utility
         * 
         */

         for (Connection con : getConnections()){
             //get reference to the router of the other node
             DTNHost other = con.getOtherNode(getHost());
             SprayAndWaitUtilityRouter otheRouter = (SprayAndWaitUtilityRouter)other.getRouter();
            if(otheRouter.isTransferring()){
                continue;
                //skip hosts that are transferring
            }
             
         }
        return tryMessagesForConnected(messages);
        //try to send
    }

}
