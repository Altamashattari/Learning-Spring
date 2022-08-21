package com.altamash.springdemoannotations;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "Tomorrow is your lucky day";
    }
    
}
