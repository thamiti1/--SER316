package main.java;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected int userAge;
    public List<Product> cart;
 // SER316 TASK 2 SPOTBUGS FIX   
 //   public int cartStorage; 

    /**
     * Calculates the final cost after all savings and tax has been applied. Also checks
     * that the user is of age to purchase alcohol if it is in their cart at checkout. 
     * Sales tax is always AZ tax.
     * Calculation is based off of the following prices and deals:
     * Dairy -> $3
     * Meat -> $10
     * Produce -> $2 or 3 for $5
     * Alcohol -> $8
     * Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     * If there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     *
     * @return double totalCost
     */
    public double calcCost() throws UnderAgeException {
        double cost = 0.0;
        double tax = 0.0;
        double totalCost = 0.0;
        
        int a = 0; //alcohol
        int f = 0; //frozen food
        int p = 0; //produce
        
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getClass() == Alcohol.class) {
                a++; 
            
            } else if (cart.get(i).getClass() == FrozenFood.class) {
                f++;
            }
   
            if (a >= 1 && f >= 1) {
                cost -= 3;
                a--;
                f--;
            }
    
            if (cart.get(i).getClass() == Produce.class) {
                p++;
    
                if (p >= 3) {
                    cost -= 1;
                    p = 0;
                }
            }
    
            cost += cart.get(i).getCost();    
        }
        tax = getTax(cost, "AZ");
        totalCost = tax + cost;
    
        return totalCost; 
    }

    // calculates how much was saved in the current shopping cart based on the deals, 
    // returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: create white box tests and 
    // fix the method, reach at least 98% coverage
    
    public int Amount_saved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;

        double produceCounter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;

        for (int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();

            if (cart.get(i).getClass().toString().equals(Produce.class.toString())) {  //SER316
                produceCounter++;    //.equals so it worked properly for coverage

                if (produceCounter >= 3) {
                    costAfterSavings -= 1;
                    produceCounter = 0;
                } 
            
            } else if (cart.get(i).getClass().toString().equals(Alcohol.class.toString())) {  
                //SER316
                alcoholCounter++;        //.equals so it worked properly for coverage
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            } else if (cart.get(i).getClass().toString().equals(FrozenFood.class.toString())) { 
                //SER 316
                frozenFoodCounter++;    //.equals so it worked for code coverage
            }
            /**SER 316 start
             * else if (cart.get(i).getClass().toString() == FrozenFood.class.toString())
            *    dairyCounter++;
            * SER 316 end 
            * code was redundant and dairy is incorrectly incremented, 
            * doesn't need to in this case
            */ 


            if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                costAfterSavings = costAfterSavings + 3;
                alcoholCounter--;
                frozenFoodCounter--;
            }
        }

        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBT, String twoLetterUSStateAbbreviation) {
        double newTotal = 0;
        switch (twoLetterUSStateAbbreviation) {
            case "AZ":
                newTotal = totalBT * .08;
                break;
            case "CA":
                newTotal = totalBT * .09;
                break;
            case "NY":
                newTotal = totalBT * .1;
                break;  //SER316 TASK 2 SPOTBUGS FIX
            case "CO":
                newTotal = totalBT * .07;
                break;
            default:
                return totalBT;
        } 
        return newTotal;
    }

    public void addItem(Product np) {
        cart.add(np);
    }

    //SER316 TASK 2 SPOTBUGS FIX
    public boolean removeItem(Product productToRemove) {
        boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) == productToRemove) {
                cart.remove(i);
                test = true;
                return test;
            }
        }
        return false;
    }

    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }
}
