package com.andy.spring.ioc.overview.container;

import com.andy.spring.ioc.overview.domain.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // create applicationContext Container
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类作为配置类
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);

        //启动应用上下文
        applicationContext.refresh();

        // 可以依赖查找
        lookupCollectionByType(applicationContext);

        applicationContext.close();

    }

    @Bean
    public Person person(){
        Person person = new Person();
        person.setAge(1);
        person.setName("aaa");
        return person;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Person> persons = listableBeanFactory.getBeansOfType(Person.class);
            System.out.println("collections of type Person : " + persons);
        }
    }

}
