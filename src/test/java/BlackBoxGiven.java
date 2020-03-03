package test.java;

import main.java.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import main.java.Cart;
import main.java.Cart1;
import main.java.Cart2;
import main.java.Cart3;
import main.java.Cart4;
import main.java.Cart5;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<Cart> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Cart>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Cart0.class},
            {Cart1.class},
            {Cart2.class},
            {Cart3.class},
            {Cart4.class},
            {Cart5.class}
        };
        return Arrays.asList(classes);
    }

    private Cart createCart(int age) throws Exception {
        Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
        return constructor.newInstance(age);
    }

    // A sample Cart

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
    
    @org.junit.Before
    public void setUp() throws Exception {

        // all carts should be set up like this

        // cart created with an age 40 shopper
        cart1 = createCart(40);
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
        
        
        cart2 = createCart(10);
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
        
        cart2B = createCart(100);
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
        
        
        cart3 = createCart(100);
        for (int i = 0; i < 2; i++) {
            cart3.addItem(new Alcohol());
       }
        for(int i = 0; i < 3; i++) {
            cart3.addItem(new Dairy());
        }
        for(int i = 0; i < 2; i++) {
            cart3.addItem(new Produce());
        }
        for(int i = 0; i < 2; i++) {
            cart3.addItem(new FrozenFood());
        }    

        cart3Expected = 35.64;
        
        cart4 = createCart(10);
        for (int i = 0; i < 2; i++) {
            cart4.addItem(new Alcohol());
       }
        for(int i = 0; i < 3; i++) {
            cart4.addItem(new Dairy());
        }
        for(int i = 0; i < 2; i++) {
            cart4.addItem(new Produce());
        }
        for(int i = 0; i < 2; i++) {
            cart4.addItem(new FrozenFood());
        }    

        cart4Expected = 35.64;
        
        cart5 = createCart(21);
        for (int i = 0; i < 2; i++) {
            cart5.addItem(new Alcohol());
       }
        for(int i = 0; i < 2; i++) {
            cart5.addItem(new Dairy());
        }    

        cart5Expected = 23.76;
        
        cart6 = createCart(4033);
        for(int i = 0; i < 2; i++) {
            cart6.addItem(new Produce());
        }
        cart6Expected = 4.32;
        
    }

    // sample test
    @Test
    public void calcCostCart1() throws UnderAgeException {
        double amount = cart1.calcCost();
        assertEquals(cart1Expected, amount, .01);
    }
    
 // Tests whether someone under age is allowed to buy products with no alcohol
 // This fails case 2 even though the person who is 10 is not buying alcohol
    @Test
    public void calcCostCart2() throws UnderAgeException {
        double amount = cart2.calcCost();
        assertEquals(cart2Expected, amount, .01);
    }
    
 // Tests whether discount on produce is applied    
    @Test
    public void calcCostCart2B() throws UnderAgeException {
        double amount = cart2B.calcCost();
        assertEquals(cart2BExpected, amount, .01);
    }
    
 // Tests whether or not the discount for alcohol and frozen food is applied
 // Fails Case 3 
    @Test
    public void calcCostCart3() throws UnderAgeException {
        double amount = cart3.calcCost();
        assertEquals(cart3Expected, amount, .01);
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
    
 // Testing small value that pass class 0 
       @Test
       public void calcCostCart6() throws UnderAgeException {
           double amount = cart6.calcCost();
           assertEquals(cart6Expected, amount, .01);
       }  
}