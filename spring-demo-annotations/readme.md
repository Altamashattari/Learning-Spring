# Notes

## Using @Qualifier with Constructors

@Qualifier is a nice feature, but it is tricky when used with Constructors.

The syntax is much different from other examples and not exactly intuitive.  Consider this the "deep end of the pool" when it comes to Spring configuration LOL :-)

You have to place the @Qualifier annotation inside of the constructor arguments.

Here's an example from our classroom example. I updated it to make use of constructor injection, with @Autowired and @Qualifier. Make note of the code in bold below:

---
package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    // define a default constructor
    public TennisCoach() {
        System.out.println(">> TennisCoach: inside default constructor");
    }
    
    @Autowired
    public TennisCoach(@Qualifier("randomFortuneService") FortuneService theFortuneService) {

        System.out.println(">> TennisCoach: inside constructor using @autowired and @qualifier");
        
        fortuneService = theFortuneService;
    }
        
    
    /*
    @Autowired
    public void doSomeCrazyStuff(FortuneService theFortuneService) {
        System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");
        fortuneService = theFortuneService;
    }
    */
    
    /*
    @Autowired
    public TennisCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }
    */
    
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}

---

For detailed documentation on using @Qualifier with Constructors, see this link in the Spring Reference Manual

<https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-autowired-annotation-qualifiers>

 ====

@Qualifier with Setter Injection

You can use similar syntax with Setter Injection. You can use @Qualifier in method arguments line, such as

 @Autowired
 public void setFortuneService(@Qualifier("randomFortuneService") FortuneService theFortuneService) {
  System.out.println(">> TennisCoach: inside setFortuneService() method");
  this.fortuneService = theFortuneService;
 }

You can also use the @Qualifier above the method name. For example, here's the syntax

 @Autowired
 @Qualifier("randomFortuneService")
 public void setFortuneService(FortuneService theFortuneService) {
  System.out.println(">> TennisCoach: inside setFortuneService() method");
  this.fortuneService = theFortuneService;
 }

Feel free to use the approach that works best for you.

## Annotations - Default Bean Names - The Special Case

Annotations - Default Bean Names ... and the Special Case

In general, when using Annotations, for the default bean name, Spring uses the following rule.

If the annotation's value doesn't indicate a bean name, an appropriate name will be built based on the short name of the class (with the first letter lower-cased).

For example:

HappyFortuneService --> happyFortuneService

---

However, for the special case of when BOTH the first and second characters of the class name are upper case, then the name is NOT converted.

For the case of RESTFortuneService

RESTFortuneService --> RESTFortuneService

No conversion since the first two characters are upper case.

Behind the scenes, Spring uses the Java Beans Introspector to generate the default bean name. Here's a screenshot of the documentation for the key method.

<https://docs.oracle.com/javase/8/docs/api/java/beans/Introspector.html#decapitalize(java.lang.String>)

As always, you can specify a name for your bean.

@Component("foo")
public class RESTFortuneService .... {

}
Then you can access it using the name of "foo". Nothing tricky to worry about :-)

Hope this helps. Happy Coding! :-)

## FAQ: How to inject properties file using Java annotations

Answer:

This solution will show you how inject values from a properties file using annotatons. The values will no longer be hard coded in the Java code.

1. Create a properties file to hold your properties. It will be a name value pair.  

New text file:  src/sport.properties

foo.email=myeasycoach@luv2code.com
foo.team=Silly Java Coders
Note the location of the properties file is very important. It must be stored in src/sport.properties

2. Load the properties file in the XML config file.

File: applicationContext.xml

Add the following lines:

    <context:property-placeholder location="classpath:sport.properties"/>  

This should appear just after the <context:component-scan .../> line

3. Inject the properties values into your Swim Coach: SwimCoach.java

@Value("${foo.email}")
private String email;

@Value("${foo.team}")
private String team;
---

DOWNLOAD FULL SOURCE CODE

You can download entire code from here:

- <http://www.luv2code.com/downloads/spring-hibernate/spring-props-annotation-demo.zip>

## Special Note about @PostConstruct and @PreDestroy Method Signatures

Access modifier

The method can have any access modifier (public, protected, private)

Return type
The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.

Method name
The method can have any method name.

Arguments
The method can not accept any arguments. The method should be no-arg.

## Special Note about Destroy Lifecycle and Prototype Scope

Here is a subtle point you need to be aware of with "prototype" scoped beans.

For "prototype" scoped beans, Spring does not call the @PreDestroy method.  Gasp!  

I didn't know this either until I dug through the Spring reference manual researching a student's question.

Here is the answer from the Spring reference manual. Section 1.5.2

<https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-prototype>

---

In contrast to the other scopes, Spring does not manage the complete lifecycle of a
prototype bean: the container instantiates, configures, and otherwise assembles a
prototype object, and hands it to the client, with no further record of that prototype
instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.

To get the Spring container to release resources held by prototype-scoped beans, try using a custom bean post-processor, which holds a reference to beans that need to be cleaned up.

This also applies to XML configuration.

---

QUESTION: How can I create code to call the destroy method on prototype scope beans

ANSWER:

You can destroy prototype beans but custom coding is required. This examples shows how to destroy prototype scoped beans.

1. Create a custom bean processor. This bean processor will keep track of prototype scoped beans. During shutdown it will call the destroy() method on the prototype scoped beans.

package com.luv2code.springdemo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyCustomBeanProcessor implements BeanPostProcessor, BeanFactoryAware, DisposableBean {

 private BeanFactory beanFactory;
 
 private final List<Object> prototypeBeans = new LinkedList<>();
 
 @Override
 public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
 
  // after start up, keep track of the prototype scoped beans. 
  // we will need to know who they are for later destruction
  
  if (beanFactory.isPrototype(beanName)) {
   synchronized (prototypeBeans) {
    prototypeBeans.add(bean);
   }
  }
 
  return bean;
 }
 
 
 @Override
 public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
  this.beanFactory = beanFactory;
 }
 
 
 @Override
 public void destroy() throws Exception {
 
  // loop through the prototype beans and call the destroy() method on each one
  
        synchronized (prototypeBeans) {
 
         for (Object bean : prototypeBeans) {
 
          if (bean instanceof DisposableBean) {
                    DisposableBean disposable = (DisposableBean)bean;
                    try {
                        disposable.destroy();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
 
         prototypeBeans.clear();
        }
        
 }
}

2. The prototype scoped beans MUST implement the DisposableBean interface. This interface defines a "destroy()" method. This method should be used instead of the @PreDestroy annotation.

package com.luv2code.springdemo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TennisCoach implements Coach, DisposableBean {

 @Autowired
 private FortuneService fortuneService;
 
 @PostConstruct
 public void doMyStartupStuff() {
  System.out.println(">> TennisCoach: inside doMyStartupStuff()");
 }
 
 @Override
 public String getDailyWorkout() {
  return "Practice your backhand volley";
 }
 
 @Override
 public String getDailyFortune() {
  return fortuneService.getFortune();
 }
 
 @Override
 public void destroy() throws Exception {
  System.out.println(">> TennisCoach: inside destroy()");
  
 }

}

3. In this app, BeanProcessorDemoApp.java is the main program. TennisCoach.java is the prototype scoped bean. TennisCoach implements the DisposableBean interface and provides the destroy() method. The custom bean processing is handled in the MyCustomBeanProcessor class.

package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanProcessorDemoApp {

 public static void main(String[] args) {
 
  // load spring config file
  ClassPathXmlApplicationContext context = new 
    ClassPathXmlApplicationContext("applicationContext.xml"); 
    
  // retrieve bean from spring container
  Coach theCoach = context.getBean("tennisCoach", Coach.class);
 
  System.out.println("Daily workout: " + theCoach.getDailyWorkout());
 
  // close the context
  context.close();
 }

}

See source code here for details: destroy-protoscope-bean-with-custom-processor.zip
