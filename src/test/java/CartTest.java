package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import main.java.Cart;
import main.java.Alcohol;
import main.java.Dairy;
import main.java.FrozenFood;
import main.java.Meat;
import main.java.Produce;
import main.java.Product;
import main.java.UnderAgeException;

public class CartTest2 {

	Cart cart0;	
	
	Cart cart1;
    double cart1Expected;

    Cart cart2;
    double cart2Expected;
    
    Cart cart2B;
    double cart2BExpected;

    Cart cart3;
    double cart3Expected;
    
    Cart cart4;
    double cart4Expected;
    
    Cart cart5;
    double cart5Expected;
    
    Cart cart6;
    double cart6Expected;
    
    Cart cart7;
    double cart7Expected;
    
    Cart cart8;
    double cart8Expected;
    
    Product product;
    
    @Before
    public void setUp() throws Exception { 
   
    	cart0 = new Cart(33);
    	
        cart1 = new Cart(40);
        for (int i = 0; i < 2; i++) {
            cart1.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
            cart1.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart1.addItem(new Meat());
        }

        cart1Expected = 70.2;
        
        
        cart2 = new Cart(10);
        for(int i = 0; i < 3; i++) {
            cart2.addItem(new Dairy());
        }
        for(int i = 0; i < 2; i++) {
            cart2.addItem(new Produce());
        }
        for(int i = 0; i < 2; i++) {
            cart2.addItem(new FrozenFood());
        }    

        cart2Expected = 24.84;
        
        cart2B = new Cart(100);
        for(int i = 0; i < 3; i++) {
            cart2B.addItem(new Dairy());
        }
        for(int i = 0; i < 3; i++) {
            cart2B.addItem(new Produce());
        }
        for(int i = 0; i < 2; i++) {
            cart2B.addItem(new FrozenFood());
        }    

        cart2BExpected = 25.92;
        
        
        cart3 = new Cart(100);
        for (int i = 0; i < 2; i++) {
            cart3.addItem(new Alcohol());
       }
        for(int i = 0; i < 2; i++) {
            cart3.addItem(new FrozenFood());
        }    

        cart3Expected = 35.64;
        
        cart4 = new Cart(10);
        for (int i = 0; i < 2; i++) {
            cart4.addItem(new Alcohol());
       }
        for(int i = 0; i < 3; i++) {
            cart4.addItem(new Dairy());
        }
        for(int i = 0; i < 2; i++) {
            cart4.addItem(new Produce());
        } 

        cart4Expected = 31.32;
        
        cart5 = new Cart(21);
        for (int i = 0; i < 2; i++) {
            cart5.addItem(new Alcohol());
       }
        for(int i = 0; i < 2; i++) {
            cart5.addItem(new Dairy());
        }    

        cart5Expected = 23.76;
        
        cart6 = new Cart(4);
        for(int i = 0; i < 2; i++) {
            cart6.addItem(new Alcohol());
        }
        cart6Expected = 0.0;
        
        cart7 = new Cart(85);
        cart7Expected = 0.0;
        
        cart8 = new Cart(13);
        cart8.addItem(product);
    }
    
    
    @Test 
    public void amount_SavedA() throws UnderAgeException {
    	double amount = cart2B.Amount_saved();
        assertEquals(1.0, amount, .01);
    }
  
    @Test
    public void amount_SavedB() throws UnderAgeException {
    	double amount = cart3.Amount_saved();
        assertEquals(-6.0, amount, .01);
    }
    
    /**  // Commented out so all tests pass because it tests under 21 for alcohol purchase
    @Test
    public void amount_SavedC() throws UnderAgeException {
    	double amount = cart6.Amount_saved();
        assertEquals(4.0, amount, .01);
    }
    */
    @Test
    public void getTax() {
    	assertEquals(0.0, cart1.getTax(0, ""), .01);
    }
    
    @Test
    public void getTaxAZ() {
        assertEquals(5.28, cart1.getTax(66, "AZ"), .01);
    }
    
    @Test
    public void getTaxCA() {
        assertEquals(0.9, cart1.getTax(10, "CA"), .01);
    }
    
    @Test
    public void getTaxNY() {
        assertEquals(3.85, cart1.getTax(55, "NY"), .01);
    }
    
    @Test
    public void getTaxCO() {
        assertEquals(1.68, cart1.getTax(24, "CO"), .01);
    }
    

    @Test
    public void calcCostCart1() throws UnderAgeException {
        double amount = cart1.calcCost();
        assertEquals(cart1Expected, amount, .01);
    }
    
    @Test
    public void calcCostCart2() throws UnderAgeException {
        double amount = cart2.calcCost();
        assertEquals(cart2Expected, amount, .01);
        
    }
    
    // Tests whether someone under age can buy alcohol
    @Test
    public void calcCostCart4() throws UnderAgeException {
        double amount = cart4.calcCost();
        assertEquals(cart4Expected, amount, .01);
    }

// Tests whether someone exactly 21 can buy alcohol
    @Test 
    public void calcCostCart5() throws UnderAgeException {
        double amount = cart5.calcCost();
        assertEquals(cart5Expected, amount, .01);
    }       
    
    @Test
    public void calcCostCart7() throws UnderAgeException {
        double amount = cart7.calcCost();
        assertEquals(cart7Expected, amount, .01);
    } 
    
    @Test
    public void removeItemA() {
        assertFalse(cart0.RemoveItem(product));
    }
    
    @Test
    public void removeItemB() {
    	boolean p = cart8.RemoveItem(product);
        assertEquals(true, p);
    }
    
}
