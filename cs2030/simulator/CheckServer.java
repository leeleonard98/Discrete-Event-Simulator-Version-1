package cs2030.simulator;

import java.util.ArrayList;
/**
 * The CheckServer class takes in a list of Servers,
 * and there are 3 methods: checkServe, checkWait and checkDone.
 * This is to check if the server is able to serve
 * and if the server is able to have a customer waiting and also to
 * check if a customer is done.
 */

public class CheckServer {

    private ArrayList<Server> server;
    private int numOfServers;
    

    public CheckServer(ArrayList<Server> server,int numOfServers) {
        this.server = server;
        this.numOfServers = numOfServers;  
            
    }

    public Server checkServe(double custTime, int ServerID, Customer c) {
        for (int i = 0;i < this.server.size();i++) {
            if (c.getCustState() == 3) {
                return this.server.get(c.getServer() - 1);
            } else if (this.server.get(i).getTime() <= custTime 
                        && this.server.get(i).getServerState() == 0) {
                return server.get(i);
            }
        } return null;
    } 
    
    public Server checkWait(int maxQ,int qSize,Customer num) {
        int [] servers = new int[server.size()];
        boolean check = false;
        boolean check2 = false;
        if (num.getGreedy() == 0){
        for (int i = 0;i < this.numOfServers;i++) {
                if (this.server.get(i).getQ() < maxQ) {
                     return server.get(i);    
                  }
               } for(int i=this.numOfServers;i<server.size();i++) {
                if (qSize < maxQ) {
                    return server.get(i); 
               }
            }
            return null;
    } else {
        if (qSize < maxQ && numOfServers!=server.size()) {
            check=true;                      
            }
        for (int i = 0;i < this.numOfServers;i++) {
            if (this.server.get(i).getQ() < maxQ) {
                check2=true;
            }
        }   
            if(check2==true && check==false) {
                Server s = server.get(0);
            for(int i=0;i<numOfServers;i++) {
                servers[i] = this.server.get(i).getQ(); 
                if(s.getQ()>server.get(i).getQ()) {
                    s=server.get(i);          
    }
            } return s;
            } else if(check2==true&&check==true) {
                Server s = server.get(0);
                for(int i=0;i<numOfServers;i++) {
                    servers[i] = this.server.get(i).getQ(); 
                    if(s.getQ()>server.get(i).getQ()) {
                        s=server.get(i); 
                    }
                } if(qSize<s.getQ()) {
                    return server.get(numOfServers);
                } else {
                    return s;
                }
            } else if(check2==false && check==true) {
                return server.get(numOfServers);
                } else {

                 return null;
             } 
            }
        } 
        
    

  
    
    public Server checkDone(Customer c) {        
        if(c.getCustState()==2) {
        return server.get(c.getServer()-1);
    }
        return null;
    } 
}

      
   


