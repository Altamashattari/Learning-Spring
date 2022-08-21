package com.altamash.springdemoannotations;

import org.springframework.stereotype.Component;

@Component
public class RestFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "Tomorrow is your lucky day";
    }
    
}
