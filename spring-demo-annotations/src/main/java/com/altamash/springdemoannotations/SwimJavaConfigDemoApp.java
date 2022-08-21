package com.altamash.springdemoannotations;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		// read spring config
		var context = new AnnotationConfigApplicationContext(
			SportConfig.class
		);
		// get the bean from spring container , using default bean id
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);

		// call a method on bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(theCoach.getEmail());
		System.out.println(theCoach.getTeam());
		// close the context
		context.close();
	}
}

