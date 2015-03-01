/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.lib.app;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.topekalabs.bigmachine.lib.testutils.SimpleContinuationRunnable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.javaflow.Continuation;
import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Topeka Labs
 */
public class ContinuationSerializationTest
{
    private static final Logger logger = LoggerFactory.getLogger(ContinuationSerializationTest.class.getName());
    
    public static final int TEST_SOCKET = 60010;
    
    @Test
    public void simpleSerializationTest() throws Exception
    {
        MutableInt context = new MutableInt();
        Continuation c = Continuation.startWith(new SimpleContinuationRunnable(),
                                                context);
        logger.debug("Is serializable: {}", c.isSerializable());
        
        Assert.assertEquals("The value of the context was not set properly",
                            SimpleContinuationRunnable.FIRST_VALUE,
                            context.getValue());
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(c);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        c = (Continuation) ois.readObject();
        ois.close();
        
        Continuation.continueWith(c, context);
        
        Assert.assertEquals("The value of the context was not set properly",
                            SimpleContinuationRunnable.SECOND_VALUE,
                            context.getValue());
    }
    
    @Ignore
    @Test
    public void networkSerializationTest() throws Exception
    {
        Socket outputSocket = null;
        ServerSocket inputSocket = null;
        
        outputSocket = new Socket("localhost", TEST_SOCKET);
        inputSocket = new ServerSocket(TEST_SOCKET);

        Continuation c = Continuation.startWith(new SimpleContinuationRunnable());

        Kryo kryo = new Kryo();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeObject(output, c);
    }
}
