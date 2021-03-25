package com.andy.spring.ioc.overview.dependency.lookup;

import com.andy.spring.ioc.overview.annotation.Super;
import com.andy.spring.ioc.overview.domain.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 * 通过名称查找
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF\\dependency-lookup-context.xml");
        // by name
//        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);

//        lookupByType(beanFactory);
//        lookupCollectionByType(beanFactory);

//        // lookupByAnnotation
//        lookupByAnnotation(beanFactory);

    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Person> beansWithAnnotation = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("collections of type Person With Annotation @Super : " + beansWithAnnotation);
        }

    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Person> persons = listableBeanFactory.getBeansOfType(Person.class);
            System.out.println("collections of type Person : " + persons);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        Person person = beanFactory.getBean(Person.class);
        System.out.println("real-time lookup by type : " + person);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<Person> personFactory = (ObjectFactory<Person>) beanFactory.getBean("personFactory");
        Person object = personFactory.getObject();

        System.out.println("lazy lookup: " + object);

    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        Person person = (Person) beanFactory.getBean("person");
        System.out.println("real-time lookup: " + person);
    }


}
