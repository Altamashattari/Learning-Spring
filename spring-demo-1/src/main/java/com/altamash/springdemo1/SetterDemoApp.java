package com.altamash.springdemo1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {
    public static void main(String[] args) {
        // load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        // retrieve bean from spring container
        CricketCoach theCoach = (CricketCoach) context.getBean("myCricketCoach", Coach.class);
        // call methods on the bean
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyFortune()); // injected via DI
        System.out.println(theCoach.getTeam());
        System.out.println(theCoach.getEmailAddress());
        // close the context
        context.close();
    }
}
