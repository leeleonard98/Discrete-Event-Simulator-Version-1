package cs2030.simulator;
/**
 * This EventComparator class compares 2 events.
 * It is done in terms of the time of the event, followed by the Id of the customer,
 * followed by the eventType of the customer, and will return and sort the
 * events accordingly.
 */
import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
  
    public int compare(Event e1, Event e2) {
        if (e1.getTime() < e2.getTime()) {
            return -1;
        }   else if (e1.getTime() == e2.getTime()) {
            if (e1.getId() < e2.getId()) {
                return -1;
            }   else if (e1.getId() == e2.getId()) {
                if (e1.getEventType() < e2.getEventType()) {
                    return -1;
                }   else {
                    return 1;
                }
            }   else {
                return 1;
            }  
        }   else {
            return 1;
        }
    }
}