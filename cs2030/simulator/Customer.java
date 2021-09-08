package cs2030.simulator;
/**
 * The Customer class will take in the time the customer
 * arrives and return a Customer object.
 * The Customer class will also then return a String when a customer arrives,
 * being served, leaves or when he is done.
 */
public class Customer {  
    private final double time;
    private final int id;
    private int Server;
    private int custState;
    private int greedy;

 

    public Customer(double time, int id, int Server, int custState,int greedy) {
        this.time=time;
        this.id=id;
        this.Server=Server;
        this.custState=custState;
        this.greedy=greedy;
        
    }

    public int getGreedy() {
        return this.greedy;
    }
    public boolean ifFakeCust() {
        if (this.id==0) {
            return true;
        } else {
            return false;
        }
    }
    public int getCustState() {
        return this.custState;
    }

    public double getTime() {
        return this.time;
    }
    public int getServer() {
        return this.Server;
    }

    public int getId() {
        return this.id;
    }

    public String toServe(double time, int serverNum, int type) {
        if (this.greedy==0) {
        if(type==1) {
        return String.format("%.3f",time) + " " + id + " served by server " + serverNum;  
        } else {
            return String.format("%.3f",time) + " " + id + " served by self-check " + serverNum;
        } 
    } else {
        if(type==1) {
            return String.format("%.3f",time) + " " + id + "(greedy) served by server " + serverNum;  
            } else {
                return String.format("%.3f",time) + " " + id + "(greedy) served by self-check " + serverNum;
            } 
    }
}

    public String toWait(double time,int serverNum, int type) {
        if(this.greedy==0) {
        if(type==1) {
        return String.format("%.3f",time) + " " + id + " waits to be served by server " + serverNum;
        } else {
            return String.format("%.3f",time) + " " + id + " waits to be served by self-check " + serverNum; 
        }
    } else {
        if(type==1) {
            return String.format("%.3f",time) + " " + id + "(greedy) waits to be served by server " + serverNum;
            } else {
                return String.format("%.3f",time) + " " + id + "(greedy) waits to be served by self-check " + serverNum; 
            }
    }
}

    public String toLeave(double time) { 
        if(this.greedy==0) {
            return String.format("%.3f",time) + " " + id + " leaves";
        } else {
            return String.format("%.3f",time) + " " + id + "(greedy) leaves";
        }
        
    }

    public String toDo(double time, int serverNum, int type) {
        if(this.greedy==0) {
        if(type==1) {
        return String.format("%.3f",time) + " " + id + " done serving by server " + serverNum;
    } else{
        return String.format("%.3f",time) + " " + id + " done serving by self-check " + serverNum;
    }
} else {
    if(type==1) {
        return String.format("%.3f",time) + " " + id + "(greedy) done serving by server " + serverNum;
    } else{
        return String.format("%.3f",time) + " " + id + "(greedy) done serving by self-check " + serverNum;
    }
}
}

    public String toArrive() {
        if(this.greedy==0) {
            return String.format("%.3f",this.time) + " " + id + " arrives";
        } else {
        return String.format("%.3f",this.time) + " " + id + "(greedy) arrives";
    }
    
}
}