/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.client;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.topekalabs.bigmachine.main.master.Command;
import java.util.List;

/**
 *
 * @author Topeka Labs
 */
@Parameters(commandDescription="Launch a big machine app.")
public class CommandLaunchBGMApp implements Command
{
    public static final String COMMAND = "launch";
    
    @Parameter(names="conf",
               required=false,
               description="This specifies an xml configuration file to be used " +
                           "with the application.")
    private String conf;
    
    @Parameter(names="resources",
               required=false,
               variableArity=true,
               description="This specifies any local resource files to be uploaded with the application")
    private List<String> resources;
    
    @Parameter(names="pkg",
               required=true,
               description="The BGM package that you want to launch.")
    private String pkg;
    
    @Override
    public void execute()
    {
    }
}
