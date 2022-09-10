package com.hibernate.tutorial.hibernatetutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.tutorial.entity.Student;

public class ReadStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating a new student object...");
            Student student = new Student("Altamash", "Ali", "altamash@gmail.com");
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();

            // Read by primary key
            System.out.println("saved student with id:" + student.getId());

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting student with ID" + student.getId());

            Student myStudent = session.get(Student.class, student.getId());
            System.out.println(myStudent);
            session.getTransaction().commit();

            System.out.println("Done!!");
        } finally {
            factory.close();
        }
    }
}
