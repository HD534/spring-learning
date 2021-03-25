package com.andy.spring.ioc.overview.dependency.injection;

import com.andy.spring.ioc.overview.repo.PersonRepo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {

//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF\\dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF\\dependency-injection-context.xml");

        // 我们自定义的 bean
        PersonRepo personRepo = applicationContext.getBean("personRepo", PersonRepo.class);
        System.out.println(personRepo.getPersons());

        // 内建的 依赖
        System.out.println(personRepo.getBeanFactory());

        System.out.println(applicationContext == personRepo.getBeanFactory());

        // 依赖查找 （错误）
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        System.out.println(personRepo.getObjectFactory().getObject());
        System.out.println(personRepo.getObjectFactory().getObject() == applicationContext);

        // 容器内建的 bean
        System.out.println("get the environment bean :\n" + applicationContext.getBean(Environment.class));
        whoIsIoCContainer(applicationContext, personRepo.getBeanFactory());


    }

    /**
     * As
     * {@link org.springframework.context.ConfigurableApplicationContext} <- {@link ApplicationContext} <- {@link BeanFactory}
     * <p>
     * Why provide the method {@link ConfigurableApplicationContext#getBeanFactory()}
     *
     * @param applicationContext
     * @param beanFactory
     */
    private static void whoIsIoCContainer(ApplicationContext applicationContext, BeanFactory beanFactory) {
        // 为什么 不成立呢？
        System.out.println(applicationContext == beanFactory);
        // 使用了组合，所以不是同一个对象
        // applicationContext is beanFactory; but applicationContext contain the basic beanFactory;

        AbstractRefreshableApplicationContext c = (AbstractRefreshableApplicationContext) applicationContext;

        System.out.println(c.getBeanFactory() == beanFactory);
    }

    /**
     * ApplicationContext 是 beanFactory 的超集
     * 面向切面 AOP
     * 配置元信息
     * 资源管理
     * 事件
     * 国际化
     * 注解
     * Environment 抽象
     */

    /**
     * BeanFactory or ApplicationContext
     * BeanFactory 是 底层的Ioc容器
     * ApplicationContext 是 BeanFactory 的超集
     */
}
