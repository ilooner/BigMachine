/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.client;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.Records;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Topeka Labs
 */
public class BGMClient
{
    private static final Logger logger = LoggerFactory.getLogger(BGMClient.class.getName());
    
    public BGMClient(String appName)
    {
        
    }
    
    private void setAppName(String appName)
    {
        
    }
    
    public void launch()
    {
        YarnClient yarnClient = YarnClient.createYarnClient();
        yarnClient.init(new Configuration());
        yarnClient.start();
        
        YarnClientApplication app = null;
        
        try
        {
            app = yarnClient.createApplication();
        }
        catch(YarnException | IOException ex)
        {
            throw new RuntimeException(ex);
        }
        
        GetNewApplicationResponse appResponse = app.getNewApplicationResponse();
        logger.debug("Application ID {}", appResponse.getApplicationId());
        logger.debug("Maximum available resources: {}", appResponse.getMaximumResourceCapability());
        
        ApplicationSubmissionContext appContext = app.getApplicationSubmissionContext();
        ApplicationId appId = appContext.getApplicationId();
        
        ContainerLaunchContext appMasterContainer = Records.newRecord(ContainerLaunchContext.class);
    }
}
