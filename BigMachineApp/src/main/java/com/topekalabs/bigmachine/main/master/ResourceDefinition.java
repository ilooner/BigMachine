/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.main.master;

import com.topekalabs.math.utils.IntUtils;

/**
 *
 * @author Topeka Labs
 */
public class ResourceDefinition
{
    private int mbs;
    private int vcpus;
    
    public ResourceDefinition(int mbs,
                              int vcpus)
    {
        setMbs(mbs);
        setVcpus(vcpus);
    }
    
    public ResourceDefinition(String mbs,
                              String vcpus)
    {
        setMbs(mbs);
        setVcpus(vcpus);
    }
    
    private void setMbs(int mbs)
    {
        IntUtils.isNonPositiveException(mbs, "mbs");
        this.mbs = mbs;
    }
    
    private void setMbs(String mbs)
    {
        setMbs(Integer.parseInt(mbs));
    }
    
    private void setVcpus(int vcpus)
    {
        IntUtils.isNonPositiveException(vcpus, "vcpus");
        this.vcpus = vcpus;
    }
    
    private void setVcpus(String mbs)
    {
        setMbs(Integer.parseInt(mbs));
    }

    /**
     * @return the mbs
     */
    public int getMbs()
    {
        return mbs;
    }

    /**
     * @return the vcpus
     */
    public int getVcpus()
    {
        return vcpus;
    }
}
