/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.main.master;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.collect.Lists;
import com.topekalabs.error.utils.ExceptionUtils;
import com.topekalabs.java.utils.ObjectUtils;
import com.topekalabs.math.utils.IntUtils;
import java.util.List;

/**
 *
 * @author Topeka Labs
 */
@Parameters(commandDescription="Launch a big machine.")
public class CommandLaunchBGM
{
    public static final String COMMAND = "launchbgm";
    
    public static final int WRDS_NUM_ARGS = 3;
    public static final int WRDS_WORKER_COUNT_OFFSET = 0;
    public static final int WRDS_MEMORY_MBS_OFFSET = 1;
    public static final int WRDS_VCPUS_OFFSET = 2;
    
    public static final int CRDS_NUM_ARGS = 2;
    public static final int CRDS_MEMORY_MBS_OFFSET = 0;
    public static final int CRDS_VCPUS_OFFSET = 1;
    
    public CommandLaunchBGM()
    {
    }
    
    @Parameter(names="wrds",
               required=true,
               variableArity=true,
               description="This provides the worker resource definitions. " +
                           "This command takes a list of triplets of parameters. " +
                           "Each triplet contains the number of workers with this " +
                           "resource definition, the amount of memory per worker, " + 
                           "and the number of vcpus per worker.")
    private List<String> wrds;
    
    @Parameter(names="crds",
               arity=2,
               required=true,
               description="This provides the controller resource definitions. " +
                           "This command takes two parameters: the amount of memory " +
                           "to be allocated to the controller and the number of vcpus " +
                           "per worker.")
    private List<String> crds;
    
    private List<ResourceDefinition> wResourceDefinitions;
    private ResourceDefinition cResourceDefinitions;
    
    public List<ResourceDefinition> getWorkerResourceDefinitions()
    {
        if(!ObjectUtils.isNull(wResourceDefinitions))
        {
            return wResourceDefinitions;
        }
        
        ExceptionUtils.thisShouldNotHappen(ObjectUtils.isNull(wrds));
        
        IntUtils.isNotEqualToException(0,
                                       wrds.size() % WRDS_NUM_ARGS,
                                       "worker args");
        
        wResourceDefinitions = Lists.newArrayList();
        
        for(int definitionIndex = 0;
            definitionIndex < wrds.size();
            definitionIndex += 3) {
            int numWorkers = Integer.parseInt(wrds.get(definitionIndex + WRDS_WORKER_COUNT_OFFSET));
            IntUtils.isNonPositiveException(numWorkers, "numWorkers");
            
            while(numWorkers-- > 0)
            {
                wResourceDefinitions.add(new ResourceDefinition(
                wrds.get(definitionIndex + WRDS_MEMORY_MBS_OFFSET),
                wrds.get(definitionIndex + WRDS_VCPUS_OFFSET)));
            }
        }
        
        return wResourceDefinitions;
    }
    
    public ResourceDefinition getControllerResourceDefinitions()
    {
        if(!ObjectUtils.isNull(cResourceDefinitions))
        {
            return cResourceDefinitions;
        }
        
        ExceptionUtils.thisShouldNotHappen(ObjectUtils.isNull(crds));
        
        IntUtils.isNotEqualToException(0,
                                       crds.size() % CRDS_NUM_ARGS,
                                       "controller args");
        
        return new ResourceDefinition(crds.get(CRDS_MEMORY_MBS_OFFSET),
                                      crds.get(CRDS_VCPUS_OFFSET));
    }
}
