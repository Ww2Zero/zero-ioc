package com.zero.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zero.ioc.base.BeanDefinition;
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
        loadFile();
    }

    private void loadFile() {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(jsonFileName);
        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {
        });
        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }


}
