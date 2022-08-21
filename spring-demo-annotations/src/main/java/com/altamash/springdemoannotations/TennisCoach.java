package com.altamash.springdemoannotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @Component("bean-id") if beanid is not specified, default beanId will be tennisCoach
@Component
public class TennisCoach implements Coach {

    // Field Injection
    @Autowired
    @Qualifier("randomFortuneService") // bean id of the component is specified as param
    private FortuneService fortuneService;

    // Constructor injection
    /*
    @Autowired
    TennisCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
    */

    // Setter Injection
    /*
     @Autowired
    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
     */

     // Method Injection
    /*
     @Autowired
     public void methodInjectionExample(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
     }
     */

    @Override
    public String getDailyWorkout() {
        return "Practice your Backend volley";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }
    
}
