package com.company;

import com.company.core.util.GcdCalc;
import com.company.core.util.impl.GcdCalcImpl;
import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGcd {
    
    private GcdCalc gcdCalc;
    
    @Before
    public void setup() {
        gcdCalc = new GcdCalcImpl();
    }
    
    @Test
    public void TestCalculatingGcd(){
        int test1 = gcdCalc.gcd(20, 16);
        Assert.assertEquals(test1, 4);
        
        int test2 = gcdCalc.gcd(7, 16);
        Assert.assertEquals(test2, 1);
        
        int test3 = gcdCalc.gcd(10, 1);
        Assert.assertEquals(test3, 1);
        
        int test4 = gcdCalc.gcd(100, 52);
        Assert.assertEquals(test4, 4);
        
        int test5 = gcdCalc.gcd(1234, 52);
        Assert.assertEquals(test5, 2);
    }
}
