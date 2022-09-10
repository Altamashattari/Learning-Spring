package com.hibernate.tutorial.hibernatetutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.tutorial.entity.Student;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            
            int studentId = 1;

            System.out.println("Getting student with ID" + studentId);

            Student myStudent = session.get(Student.class, studentId);

            // update first name
            myStudent.setFirstName("Shamshad");
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            session
                .createQuery("UPDATE Student set email='foo@gmail.com'")
                .executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!!");
        } finally {
            factory.close();
        }
    }
}
