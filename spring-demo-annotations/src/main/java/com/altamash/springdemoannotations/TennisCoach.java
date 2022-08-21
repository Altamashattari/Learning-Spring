package com.altamash.springdemoannotations;

import org.springframework.stereotype.Component;

// @Component("bean-id") if beanid is not specified, default beanId will be tennisCoach
@Component
public class TennisCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice your Backend volley";
    }
    
}
