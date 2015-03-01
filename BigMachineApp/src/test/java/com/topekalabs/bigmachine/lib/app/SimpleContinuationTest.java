/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.lib.app;

import com.topekalabs.bigmachine.lib.testutils.SimpleContinuationRunnable;
import org.apache.commons.javaflow.Continuation;
import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Topeka Labs
 */
public class SimpleContinuationTest
{
    @Test
    public void simpleTest()
    {
        MutableInt context = new MutableInt();
        Continuation c = Continuation.startWith(new SimpleContinuationRunnable(),
                                                context);
        
        Assert.assertEquals("The value of the context was not set properly",
                            SimpleContinuationRunnable.FIRST_VALUE,
                            context.getValue());
        
        Continuation.continueWith(c, context);
        
        Assert.assertEquals("The value of the context was not set properly",
                            SimpleContinuationRunnable.SECOND_VALUE,
                            context.getValue());
    }
}
