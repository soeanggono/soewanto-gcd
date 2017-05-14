package com.company.core.util.impl;

import com.company.core.util.GcdCalc;
import javax.inject.Named;

@Named("gcdCalc")
public class GcdCalcImpl implements GcdCalc {

    /*
     * This code is taken from
     * https://en.wikipedia.org/wiki/Binary_GCD_algorithm
     */
    @Override
    public int gcd(int i1, int i2) {
        int shift = 0;
        
        if (i1 == 0) return i2;
        if (i2 == 0) return i1;
        
        while (((i1 % 2) == 0) && ((i2 % 2) == 0)) {
            i1 >>= 1;
            i2 >>= 1;
            shift++;
        }
        
        while ((i1 % 2) == 0) {
            i1 >>= 1;
        }
        
        do {
            while ((i2 % 2) == 0) {
                i2 >>= 1;
            }
            
            if (i1 > i2) {
                int temp = i1;
                i1 = i2;
                i2 = temp;
            }
            
            i2 = i2 - i1;
        } while (i2 != 0);
        
        return i1 << shift;
    }
}
