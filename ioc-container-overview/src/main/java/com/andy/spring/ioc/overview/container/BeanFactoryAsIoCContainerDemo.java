package com.andy.spring.ioc.overview.container;

import com.andy.spring.ioc.overview.domain.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        // create beanFactory Container
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // load config
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF\\dependency-lookup-context.xml";
        int BeanCount = xmlBeanDefinitionReader.loadBeanDefinitions(location);
        System.out.println("BeanCount: " + BeanCount);
        // 可以依赖查找
        System.out.println(beanFactory.getBean(Person.class));

    }

}
