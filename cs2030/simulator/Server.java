package cs2030.simulator;

import java.util.ArrayList;

/**
 * The Server class is the Server object. This class takes in the ID of the
 * Server, the current time, the number of customers in queue and the state
 * the server.
 */

public class Server {
    private int num;
    public double currentT;
    private int numInQ;
    private int serverState; 
    private ArrayList<Customer> customerInQ;  
    

    public Server(int num, double currentT, int numInQ, int serverState) {
        this.num = num;
        this.currentT = currentT;
        this.numInQ = numInQ;
        this.serverState = serverState;
        this.customerInQ = new ArrayList<Customer>();
        
    }
  
    public int getNum() {
        return this.num;
    }  

    public Customer getCust() {
        return this.customerInQ.get(0);
    }
    

    public int getQ() {
        return this.numInQ;
    }
    
    public int getServerState() {
        return this.serverState;
    }
    
    public void addCust(Customer num) {
        this.customerInQ.add(num);
    }
    public void removeCust() {
        this.customerInQ.remove(0);
    }

    public void serverRest() {
        this.serverState++;
    }
    public void serverBack() {
        this.serverState--;
    }

    public void serve(double custTime,double time) {
        this.currentT = custTime + time;
        
    }  

    public void wait2() {
        this.numInQ++;
        
    }
 
    public void waitOver() {
        if(this.numInQ>0) {
        this.numInQ--;
        }
    }
 
     
    public double getTime() {
        return this.currentT;
    }
    
}


  

    
