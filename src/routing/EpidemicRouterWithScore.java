package routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.Connection;
import core.DTNHost;
import core.Message;
import core.Settings;

public class EpidemicRouterWithScore extends ActiveRouter {

    protected HashMap<Integer,Integer> score;

    public EpidemicRouterWithScore(Settings s) {
        super(s);
        score = new HashMap<Integer,Integer>();
       
    }
   
    public EpidemicRouterWithScore(EpidemicRouterWithScore r){
        super(r);
        score = new HashMap<Integer,Integer>();
    }

    @Override
    public void changedConnection(Connection con){
        super.changedConnection(con);

        if(con.isUp()){
            int baseScoreValue = 100;
            int connectedNodeAddress = con.getOtherNode(getHost()).getAddress();
            if(score.containsKey(connectedNodeAddress)){
                baseScoreValue = score.get(connectedNodeAddress);

            }
            score.put(connectedNodeAddress,baseScoreValue + 5);
            
        }
    }
    @Override
    public void update(){
        super.update();

        if(isTransferring() || !canStartTransfer()){
            return;
            //The node is transferring rightnow , cannot try any other connections yet
            
        }
        //First the messages that can be delivered to final recipient are tried
        if (exchangeDeliverableMessages()!=null){
            return;

        }

        //then try all messages but only to specific connections with greeater scores
        this.tryAllMessagesToAllConnectionsWithGreaterScore();
    }
    
    protected int getCurrentScore(int nodeAddress){
        int currentScoreValue =100;
        if(score.containsKey(nodeAddress)){
            currentScoreValue = score.get(nodeAddress);
        }
        return currentScoreValue;
    }

    protected Connection tryAllMessagesToAllConnectionsWithGreaterScore(){
        List<Connection> connectionsWithGreaterScore = new ArrayList<Connection>();
        List<Connection> connections = getConnections();

        if(connections.size() == 0 || this.getNrofMessages() == 0){
            return null;
        }
        List<Message> messages = new ArrayList<Message>(this.getMessageCollection());
        this.sortByQueueMode(messages);
        for (Connection con : connections){
            DTNHost node = con.getOtherNode(getHost());
            int nodeAddress = node.getAddress();
            EpidemicRouterWithScore nodeRouter = (EpidemicRouterWithScore)node.getRouter();
        if(getCurrentScore(nodeAddress) < nodeRouter.getCurrentScore(getHost().getAddress())){
            connectionsWithGreaterScore.add(con);
        }        }

        return tryMessagesToConnections(messages, connectionsWithGreaterScore);
    }

    @Override
    public EpidemicRouterWithScore replicate(){
        return new EpidemicRouterWithScore(this);
    }
}
