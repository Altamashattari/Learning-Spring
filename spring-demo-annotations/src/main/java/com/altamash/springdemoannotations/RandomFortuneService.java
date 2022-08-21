package com.altamash.springdemoannotations;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

    // create an array of strings
    private String[] data = {
        "Fortune 1",
        "Fortune 2",
        "Fortune 3",
    };

    // randome number generator
    private Random myRandom = new Random();

    @Override
    public String getFortune() {
        int randomIndex = myRandom.nextInt(data.length);
        return data[randomIndex];
    }
    
}
