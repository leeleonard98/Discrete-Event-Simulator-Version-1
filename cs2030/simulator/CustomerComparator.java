package cs2030.simulator;

import java.util.Comparator;
/**
 * The Customer comparator compares 2 customers.
 * It is done in terms of the time of the customer,
 * followed by the id of the customer
 */

public class CustomerComparator implements Comparator<Customer>{    
      
        public int compare(Customer c1, Customer c2) {
            if (c1.getTime() < c2.getTime()) {
                return -1;           
            }   else if (c1.getTime() == c2.getTime()) {
                if (c1.getId() < c2.getId()) {
                        return -1;
                  }  else {
                            return 1;
                        }
               
            } else {
                return 1;
            }
       
            }
    }
