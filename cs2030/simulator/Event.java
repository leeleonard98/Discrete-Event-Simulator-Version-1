package cs2030.simulator;
/**
 * The Event class is the event of what the Customer is doing.
 * This class takes in the time, the event, the eventType
 * and the customer id. 
 */
public class Event {
    private final double time;
    private final String event;
    private final int eventType;
    private final int customerId;
  
    public Event(double time, String event, int eventType, int customerId) {
        this.time = time;
        this.event = event;
        this.eventType = eventType;
        this.customerId = customerId;
    }
    
    public int getEventType() {
        return this.eventType;
    }
    
    public int getId() {
        return this.customerId;
    }
    
    public double getTime() {
        return this.time;
    }
    
    public String toString() {
        return event;
    }
}
