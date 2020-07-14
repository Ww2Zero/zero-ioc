package com.zero.ioc.core;

import com.zero.ioc.beans.exception.BeanDefinitionStoreException;
import com.zero.ioc.beans.factory.BeanDefinition;
import com.zero.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author zero
 */
public class JsonApplicationContext extends DefaultBeanFactory {


    private String jsonFileName;

    public JsonApplicationContext(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        loadJsonFile();
    }

    private void loadJsonFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(jsonFileName);
        List<BeanDefinition> beanDefinitions = null;
        try {
            beanDefinitions = JsonUtils.jsonToBeanDefinition(is);
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("load json fail", e);
        }
        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getBeanName(), beanDefinition);
            }
        }
    }


}
