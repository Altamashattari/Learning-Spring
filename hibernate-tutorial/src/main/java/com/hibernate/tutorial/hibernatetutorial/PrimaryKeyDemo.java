package com.hibernate.tutorial.hibernatetutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.tutorial.entity.Student;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating three student object...");
            Student student1 = new Student("Altamash", "Ali", "altamash@gmail.com");
            Student student2 = new Student("Sarfraz", "Ali", "sarfraz@gmail.com");
            Student student3 = new Student("Virat", "Kohli", "vk@gmail.com");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.getTransaction().commit();
            System.out.println("Done!!");
        } finally {
            factory.close();
        }
    }
}
