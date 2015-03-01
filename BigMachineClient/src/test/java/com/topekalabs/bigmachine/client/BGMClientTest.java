/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.client;

import org.junit.Test;

/**
 *
 * @author Topeka Labs
 */
public class BGMClientTest
{
    @Test
    public void testing()
    {
        BGMClient bgClient = new BGMClient("");
        bgClient.launch();
    }
}
