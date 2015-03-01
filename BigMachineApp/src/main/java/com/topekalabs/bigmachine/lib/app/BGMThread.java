/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.lib.app;

import com.topekalabs.collection.NNHashMap;
import com.topekalabs.error.utils.CodingError;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Topeka Labs
 */
public abstract class BGMThread
{
    private boolean launched = false;
    
    private NNHashMap<String, Set<Byte>> getRequestsByte = new NNHashMap<String, Set<Byte>>();
    private NNHashMap<String, Set<Integer>> getRequestsInt = new NNHashMap<String, Set<Integer>>();
    private NNHashMap<String, Set<Long>> getRequestsLong = new NNHashMap<String, Set<Long>>();
    private NNHashMap<String, Set<Float>> getRequestsFloat = new NNHashMap<String, Set<Float>>();
    private NNHashMap<String, Set<Double>> getRequestsDouble = new NNHashMap<String, Set<Double>>();
    
    private NNHashMap<String, Map<Long, Byte>> getByte = new NNHashMap<String, Map<Long, Byte>>();
    private NNHashMap<String, Map<Long, Integer>> getInt = new NNHashMap<String, Map<Long, Integer>>();
    private NNHashMap<String, Map<Long, Long>> getLong = new NNHashMap<String, Map<Long, Long>>();
    private NNHashMap<String, Map<Long, Float>> getFloat = new NNHashMap<String, Map<Long, Float>>();
    private NNHashMap<String, Map<Long, Double>> getDouble = new NNHashMap<String, Map<Long, Double>>();
    
    public final void launch()
    {
        if(launched)
        {
            throw new CodingError("Cannot Launch Thread Twice");
        }
        
        launched = true;
        
        
    }
    
    public final void getRequestInt(String arrayName, long index)
    {   
    }
    
    public final void getRequestLong(String arrayName, long index)
    {   
    }
    
    public final void getRequestFloat(String arrayName, long index)
    {
        
    }
    
    public final void getRequestDouble(String arrayName, long index)
    {
        
    }
    
    public final void getInt(String arrayName, long index)
    {   
    }
    
    public final void getLong(String arrayName, long index)
    {   
    }
    
    public final void getFloat(String arrayName, long index)
    {
    }
    
    public final void getDouble(String arrayName, long index)
    {
    }
    
    public final void setRequestInt(String arrayName, long index)
    {   
    }
    
    public final void setRequestLong(String arrayName, long index)
    {   
    }
    
    public final void setRequestFloat(String arrayName, long index)
    {
    }
    
    public final void setRequestDouble(String arrayName, long index)
    {
    }
    
    public final void setRemoteInt(String arrayName, long index)
    {
    }
    
    public final void setRemoteLong(String arrayName, long index)
    {
    }
    
    public final void setRemoteFloat(String arrayName, long index)
    {
    }
    
    public final void setRemoteDouble(String arrayName, long index)
    {
    }
}
