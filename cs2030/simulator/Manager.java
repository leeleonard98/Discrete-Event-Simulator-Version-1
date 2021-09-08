package cs2030.simulator;


/**
 * The Manager class takes in the values needed to
 * generate the random values.
 * There are 5 methods in Manager class that returns 
 * arrival time, service time, random rest of server,
 * rest period of server and also to see if a customer is 
 * greedy. 
 */
public class Manager {
    int seed;
    double lamda;
    double mu;
    double restingRate;
    static RandomGenerator rgen;

    public Manager (int seed,double lamda, double mu, double restingRate) {
        rgen = new RandomGenerator(seed,lamda,mu,restingRate);
    }
  

    public double getArrivalTime() {
     double timeInterval = rgen.genInterArrivalTime(); 
     return timeInterval;  
    }

    public double getServiceTime() {
        double timeInterval =rgen.genServiceTime();
        return timeInterval;
    }

 

    public double getRandomRest() {
        double rest = rgen.genRandomRest();
        return rest;
    }

    public double getRestPeriod() {
        double restTime = rgen.genRestPeriod();
        return restTime;
    }
    
    public double getGreedyCust() {
        double prob = rgen.genCustomerType();
        return prob;
    }


}