package designs.ratefinder.carrental;

import designs.ratefinder.vendors.Avis;
import designs.ratefinder.vendors.Budget;
import designs.ratefinder.vendors.Dollar;

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
