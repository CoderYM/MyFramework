package com.allinpay.generator.ibatis;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

public class FreeMarkerConfig {

    /**
     * Default constructor. 
     */
    public FreeMarkerConfig() {
        super();
    }

    /***�1�7
     * @param templateDirPath
     * @throws ConfigureException
     */
    public void configureFreeMarker(String templateDirPath) throws Exception {
        if (null==templateDirPath || "".equals(templateDirPath)) {
            throw new Exception("FreeMarker template directory not exist:"+ templateDirPath);
        }
        File templateDir = new File(templateDirPath);
        if (null==templateDir || !templateDir.exists() || !templateDir.isDirectory()) {
            throw new Exception("FreeMarker template directory not exist:"+ templateDirPath);
        }
        
        // Configure the template engine
        Configuration templateCfg = new Configuration();
        try {
            BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
            wrapper.setSimpleMapWrapper(true);
            wrapper.setExposureLevel(BeansWrapper.EXPOSE_ALL);
            templateCfg.setEncoding(Locale.CHINA, "utf-8");
            templateCfg.setObjectWrapper(wrapper);
            templateCfg.setDirectoryForTemplateLoading(templateDir);
            templateCfg.setNumberFormat("######################.##");
        } catch (IOException e) {
            throw new Exception("IOException occured when loading freemarker template:" + templateDirPath);
        }
        templateCfg.setTemplateUpdateDelay(2);
        Configuration.setDefaultConfiguration(templateCfg);
    }
}
