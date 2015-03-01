/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.common.collect.Maps;
import com.topekalabs.bigmachine.main.master.Command;
import com.topekalabs.error.utils.ErrorThrower;
import com.topekalabs.error.utils.ErrorThrowerFactory;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Topeka Labs
 */
public class BGMCLI
{
    public static final String ERROR_NAMESPACE = "com.topekalabs.bigmachine.client";
    public static final ErrorThrower ERROR_THROWER = ErrorThrowerFactory.createThrower(ERROR_NAMESPACE);
    
    private static final Logger logger = LoggerFactory.getLogger(BGMCLI.class.getName());
    
    public static void main(String[] args)
    {
        JCommander jCommander = new JCommander();
        Map<String, Command> commandMap = Maps.newHashMap();
        
        // Commands
        
        CommandLaunchBGMApp launchCommand = new CommandLaunchBGMApp();
        
        // Add Them
        
        jCommander.addCommand(CommandLaunchBGMApp.COMMAND, launchCommand);
        commandMap.put(CommandLaunchBGMApp.COMMAND, (Command) launchCommand);
        
        try
        {
            jCommander.parse(args);
        }
        catch(ParameterException ex)
        {
            logger.info(ex.getMessage());
            return;
        }
        
        Command command = commandMap.get(jCommander.getParsedCommand());
        
        if(command == null)
        {
            jCommander.usage();
            return;
        }
        
        command.execute();
    }
}
