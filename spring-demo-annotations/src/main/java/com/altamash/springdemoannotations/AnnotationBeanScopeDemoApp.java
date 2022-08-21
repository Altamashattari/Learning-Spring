package com.altamash.springdemoannotations;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		// read spring config
		var context = new ClassPathXmlApplicationContext(
			"applicationContext.xml"
		);
		// get the bean from spring container , using default bean id
		Coach theCoach1 = context.getBean("tennisCoach", Coach.class);
        Coach theCoach2 = context.getBean("tennisCoach", Coach.class);

		// call a method on bean
		boolean result = theCoach1 == theCoach2;

        System.out.println("\nPointing to the same object: "+ result);
        System.out.println("\nMemory location for theCoach1: "+ theCoach1);
        System.out.println("\nMemory location for theCoach2: "+ theCoach2);
		// close the context
		context.close();
	}

}
