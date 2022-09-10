package com.hibernate.tutorial.hibernatetutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.tutorial.entity.Student;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            int studentId = 2;

            // System.out.println("Getting student with ID" + studentId);

            // Student myStudent = session.get(Student.class, studentId);

            // // delete
            // session.delete(myStudent);

            session.createQuery("delete from Student where id=1").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!!");
        } finally {
            factory.close();
        }
    }
}
