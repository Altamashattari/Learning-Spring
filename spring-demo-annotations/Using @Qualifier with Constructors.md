
# Using @Qualifier with Constructors

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
