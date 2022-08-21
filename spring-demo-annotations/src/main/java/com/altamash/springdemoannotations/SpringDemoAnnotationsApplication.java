package com.altamash.springdemoannotations;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemoAnnotationsApplication {

	public static void main(String[] args) {
		// read spring config
		var context = new ClassPathXmlApplicationContext(
			"applicationContext.xml"
		);
		// get the bean from spring container , using default bean id
		Coach theCoach = context.getBean("tennisCoach", Coach.class);

		// call a method on bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		// close the context
		context.close();
	}

}
