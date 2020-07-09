package com.zero.ioc.core;

import com.zero.ioc.beans.factory.BeanDefinition;
import com.zero.ioc.utils.JsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zero
 */
public class JsonApplicationContext extends DefaultBeanFactory {


    private String jsonFileName;

    public JsonApplicationContext(String jsonFileName) throws IOException {
        this.jsonFileName = jsonFileName;
        loadFile();
    }

    private void loadFile() throws IOException {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(jsonFileName);
        List<BeanDefinition> beanDefinitions = JsonUtils.jsonToBeanDefinition(is);
        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getBeanName(), beanDefinition);
            }
        }
    }


}
