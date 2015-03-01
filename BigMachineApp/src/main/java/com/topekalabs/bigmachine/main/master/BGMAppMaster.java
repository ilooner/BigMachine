/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.main.master;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.internal.Maps;
import com.topekalabs.error.utils.RecoverableError;
import com.topekalabs.java.utils.ObjectUtils;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Topeka Labs
 */
public class BGMAppMaster
{
    private static final Logger logger = LoggerFactory.getLogger(BGMAppMaster.class.getName());
    
    public static void main(String[] args)
    {
        JCommander jCommander = new JCommander();
        Map<String, Command> commandMap = Maps.newHashMap();
        
        // Commands
        
        CommandLaunchBGM launchCommand = new CommandLaunchBGM();
        
        // Add Them
        
        jCommander.addCommand(CommandLaunchBGM.COMMAND, launchCommand);
        commandMap.put(CommandLaunchBGM.COMMAND, (Command) launchCommand);
        
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
        
        try
        {
            command.execute();
        }
        catch(RecoverableError error)
        {
            logger.info(error.getError().getMessage(), error.getError().getErrorData());

            if(!ObjectUtils.isNull(error.getCause()))
            {
                logger.debug("Exception:", error.getCause());
            }
        }
    }
}
