/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.lib.testutils;

import java.io.Serializable;
import org.apache.commons.javaflow.Continuation;
import org.apache.commons.lang.mutable.MutableInt;

/**
 *
 * @author Topeka Labs
 */
public class SimpleContinuationRunnable implements Runnable, Serializable
{
    public static final Integer FIRST_VALUE = 5;
    public static final Integer SECOND_VALUE = 10;
    
    private int count = 0;
    
    @Override
    public void run()
    {
        MutableInt val = (MutableInt) Continuation.getContext();
        val.setValue(FIRST_VALUE);
        count = SECOND_VALUE;
        
        Continuation.suspend();
        
        val = (MutableInt) Continuation.getContext();
        val.setValue(count);
    }
}
