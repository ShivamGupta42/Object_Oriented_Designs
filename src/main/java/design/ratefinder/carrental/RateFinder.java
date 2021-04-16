package design.ratefinder.carrental;

import design.ratefinder.vendors.Avis;
import design.ratefinder.vendors.Budget;
import design.ratefinder.vendors.Dollar;

public class RateFinder {
    Avis avis = new Avis();
    Budget budget = new Budget();
    Dollar dollar = new Dollar();

    public int getRates(String city, String startDate, String numberOfDays, int size){

        int res1 = avis.getRate(city,startDate,numberOfDays,size);
        int res2 = budget.getRate(city,startDate,numberOfDays,size);
        int res3 = dollar.getRate(city,startDate,numberOfDays,size);
        return -1;
    }
}
