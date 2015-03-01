/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.lib.api;

import com.topekalabs.bigmachine.lib.app.BGMAppContext;

/**
 *
 * @author Topeka Labs
 */
public interface BGMApp
{
    public void start(BGMAppContext context);
    public void beginIteration();
    public void executeIteration();
    public void endIteration();
    public void stop();
}
