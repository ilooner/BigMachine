/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.bigmachine.client.bgmpackage;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.topekalabs.bigmachine.client.BGMCLI;
import com.topekalabs.bigmachine.lib.api.BGMApp;
import com.topekalabs.bigmachine.lib.api.BGMAppMeta;
import com.topekalabs.error.utils.ExceptionUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author Topeka Labs
 */
public class BGMPackageZipped
{
    public static final String TEMP_DIR_PREFIX = "BGM";
    
    public static final String META_DIR = "meta";
    public static final String JARS_DIR = "jars";
    public static final String JARS_PROJ_DIR = JARS_DIR + File.separator + "proj";
    public static final String JARS_DEPS_DIR = JARS_DIR + File.separator + "deps";
    
    public static final Set<String> DIRS = ImmutableSet.of(META_DIR,
                                                           JARS_DIR,
                                                           JARS_PROJ_DIR,
                                                           JARS_DEPS_DIR);
    
    public static final Pattern APP_CLASS_PATTERN = Pattern.compile(BGMApp.class.getName());
    
    private Path tmpDir;
    private Map<String, Class> applicationNameToClasses;
    
    public BGMPackageZipped(File bgmPackage) throws IOException
    {
        tmpDir = Files.createTempDirectory(TEMP_DIR_PREFIX,
                       PosixFilePermissions.asFileAttribute(
                       Sets.newHashSet(PosixFilePermission.OWNER_EXECUTE,
                                       PosixFilePermission.OWNER_READ,
                                       PosixFilePermission.OWNER_WRITE)));
        tmpDir.toFile().deleteOnExit();
        
        ZipInputStream zis = new ZipInputStream(new FileInputStream(bgmPackage));
        ZipEntry ze = null;
        
        while((ze = zis.getNextEntry()) != null)
        {
            if(ze.isDirectory())
            {
                File dir = tmpDir.resolve(ze.getName()).toFile();
                
                if(!DIRS.contains(dir.getName()))
                {
                    ExceptionUtils.thisShouldNotHappen(dir.getName() +
                                                       " is not one of: " +
                                                       DIRS);
                }
                
                dir.mkdirs();
            }
            else
            {
                Files.copy(zis,
                           tmpDir.resolve(ze.getName()),
                           StandardCopyOption.COPY_ATTRIBUTES);
            }
        }
    }
    
    public Map<String, Class> getApplicationNameToClasses()
    {
        if(applicationNameToClasses != null) {
            return applicationNameToClasses;
        }
        
        URL projDirURL = null;
        
        try
        {
            projDirURL = tmpDir.resolve(JARS_PROJ_DIR).toUri().toURL();
        }
        catch(MalformedURLException ex)
        {
            BGMCLI.ERROR_THROWER.rethrowUnrecoverable("BGMPKG_TEMP_URL_ERROR", ex, projDirURL);
        }

        Reflections reflections = new Reflections(new ConfigurationBuilder().
                                  setUrls(projDirURL).
                                  setScanners(new SubTypesScanner()));
        
        Set<String> applicationClassNames = reflections.getResources(APP_CLASS_PATTERN);
        applicationNameToClasses = Maps.newHashMap();
        
        for(String applicationClassName: applicationClassNames)
        {
            Class applicationClass = null;
            
            try
            {
                applicationClass = Class.forName(applicationClassName);
            }
            catch(ClassNotFoundException ex)
            {
                ExceptionUtils.thisShouldNotHappen(ex);
            }
            
            BGMAppMeta appMeta = (BGMAppMeta) applicationClass.getAnnotation(BGMAppMeta.class);
            
        }
        
        applicationNameToClasses = Collections.unmodifiableMap(applicationNameToClasses);

        return applicationNameToClasses;
    }
}
