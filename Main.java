import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import cs2030.simulator.CheckServer;
import cs2030.simulator.Customer;
import cs2030.simulator.CustomerComparator;
import cs2030.simulator.Event;
import cs2030.simulator.EventComparator;
import cs2030.simulator.Manager;
import cs2030.simulator.Server;


/**
 * The Main class will detect which customer will arrive according to the time
 * and determine if the customer is freedy and then check if
 * the customer serves, waits, or leaves.
 * This Main class will ensure that only 1 customer is being served the
 * number of customer waiting in queue not more than the max number in queue.
 * The other customers will leave.
 * The Main class will then print out the sequence of the events, sorted 
 * by the class EventComparator, thereafter determining 
 * the average waiting time of customers being served,
 * the number of customers served and the number of customers who left without being served.
 */ 
class Main {
  
    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);
        int i = 0;
        final ArrayList<Customer> customer = new ArrayList<Customer>();
        final int arrive = 1;
        final int serve = 2;
        final int wait = 3;
        final int done = 4;
        final int leave = 5;
        final int seed = sc.nextInt();
        final int numOfServers = sc.nextInt();
        final int numCounters = sc.nextInt();
        final int maxQ = sc.nextInt();
        final int numOfCust = sc.nextInt();
        final double lamda = sc.nextDouble();
        final double mu = sc.nextDouble();
        final double restingRate = sc.nextDouble();
        final double probRR = sc.nextDouble();
        final double probGreedy = sc.nextDouble();
        final ArrayList<Server> server = new ArrayList<Server>();
        final ArrayList<Customer> selfCheckQ = new ArrayList<Customer>();
        
        for (i = 0;i < numOfServers + numCounters;i++) {
            final Server s = new Server(i + 1,0,0,0);
            server.add(s);
        }
        
        
        final CheckServer checkServer = new CheckServer(server, numOfServers);
        i = 0;
        Manager manager = new Manager(seed, lamda, mu, restingRate);
        double initialTime = 0;
        double time = 0.0;

        for (i = 0;i < numOfCust;i++) {            
            if (i > 0) {
                time = manager.getArrivalTime() + initialTime;
                initialTime = time;
            }
            boolean greedy = manager.getGreedyCust() < probGreedy;
            if (greedy == true) {
                final Customer c = new Customer(time,i + 1,1,0,1);
                customer.add(c);
            } else {
                final Customer c = new Customer(time,i + 1,1,0,0);
                customer.add(c);   
            }
        }

        PriorityQueue<Customer> customers = new PriorityQueue<Customer>(new CustomerComparator());
        final PriorityQueue<Event> pqueue = new PriorityQueue<Event>(new EventComparator());
        double countX = 0.000;
        int countY = 0;
        int countZ = 0;
        
        for (final Customer num: customer) {
            pqueue.add(new Event(num.getTime(),num.toArrive(),arrive, num.getId())); 
            customers.add(num);
        }
        int type = 1; 
        
        while (customers.size() > 0) {    
            Customer num = customers.peek();
            if (num.ifFakeCust() == true) {
                Server s = server.get(num.getServer() - 1);
                s.serverBack();
                customers.remove(num);
            } else if (checkServer.checkDone(num) != null) {
                Server s = checkServer.checkDone(num);
                if (s.getNum() > numOfServers) {
                    type = 2;
                } else {
                    type = 1;
                }
                pqueue.add(new Event(num.getTime(),num.toDo(
                            num.getTime(),s.getNum(),type),done, 
                            num.getId()));
                customers.remove(num); 
                if (s.getNum() <= numOfServers) {
                    double chanceOfRest = manager.getRandomRest(); 
                    boolean goingToRest =  chanceOfRest < probRR;
                    
                    if (s.getQ() > 0 && goingToRest == false) {
                        Customer c = s.getCust();
                        countX += num.getTime() - c.getTime();
                        c = new Customer(num.getTime(),c.getId(),s.getNum(),3,c.getGreedy());
                        customers.add(c);
                        s.removeCust();
                    }            

                    if (goingToRest == true) {
                        time = manager.getRestPeriod();
                  
                        if (s.getQ() > 0) {
                            Customer c = s.getCust();
                            c = new Customer(num.getTime() + time,c.getId(),
                                    s.getNum(),3,c.getGreedy());
                            customers.add(c); 
                            countX += num.getTime() - s.getCust().getTime() + time;
                            s.removeCust();
                        } 
                        s.serverRest();
                        Customer c = new Customer(time + num.getTime(),0,
                                    s.getNum(),1,num.getGreedy());
                        s.serve(num.getTime(),time);
                        customers.add(c);
                    }
                } else {
                    if (selfCheckQ.size() > 0) {
                        Customer c = selfCheckQ.get(0);
                        countX += num.getTime() - c.getTime();
                        c = new Customer(num.getTime(),c.getId(),s.getNum(),3,c.getGreedy());
                        customers.add(c);
                        selfCheckQ.remove(0); 
                    }
                }
                
            } else if (checkServer.checkServe(num.getTime(),num.getServer(),num) != null) {
                time = manager.getServiceTime();   
                Server s = checkServer.checkServe(num.getTime(),num.getServer(),num);
                if (s.getNum() > numOfServers) {
                    type = 2;
                } else {
                    type = 1;
                }
                pqueue.add(new Event(num.getTime(),num.toServe(num.getTime(), 
                            s.getNum(),type),serve, num.getId()));
                countY++;
               
                Customer c = new Customer(num.getTime() + time, num.getId(),
                            s.getNum(),serve,num.getGreedy());
                customers.add(c);
                s.serve(num.getTime(),time); 
                s.waitOver();
                customers.remove(num); 
            
            } else if (checkServer.checkWait(maxQ,selfCheckQ.size(),num) != null) {                
                Server s = checkServer.checkWait(maxQ,selfCheckQ.size(),num);
                if (s.getNum() > numOfServers) {
                    pqueue.add(new Event(num.getTime(),num.toWait(
                        num.getTime(),numOfServers + 1,2),wait,num.getId())); 
                    selfCheckQ.add(num);
                    
                } else {                   
                    pqueue.add(new Event(num.getTime(),num.toWait(
                        num.getTime(),s.getNum(),1),wait,num.getId()));  
                                
                    if (s.getServerState() == 1 && s.getQ() == 0) {
                        Customer c = new Customer(s.currentT,num.getId(),s.getNum(),3,0);
                        customers.add(c);    
                        countX += s.currentT - num.getTime();                
                    } else {
                        s.addCust(num);
                    }
                }
                s.wait2();            
                customers.remove(num);
           

            } else {
                pqueue.add(new Event(num.getTime(),num.toLeave(num.getTime()),leave,num.getId()));
                countZ++;
                customers.remove(num);
            }
        }
          
            
    
        while (pqueue.size() > 0) {          
            System.out.println(pqueue.poll());
        } 
        if (countY == 0) {
            System.out.println("[" + String.format("%.3f", 0.000)
                                 + " " + countY + " " + countZ + "]");
        } else {
            System.out.println("[" + String.format("%.3f", countX / (double)countY)
                                 + " " + countY + " " + countZ + "]");
        }
    }
    
    
}