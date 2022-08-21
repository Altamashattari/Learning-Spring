package com.altamash.springdemoannotations;

public class SadFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "You will have bad day today!";
    }
    
}
