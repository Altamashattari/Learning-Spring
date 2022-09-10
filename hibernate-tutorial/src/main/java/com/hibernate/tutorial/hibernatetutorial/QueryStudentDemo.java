package com.hibernate.tutorial.hibernatetutorial;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.tutorial.entity.Student;

public class QueryStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            List<Student> students = session
                    .createQuery("from Student")
                    .getResultList();
            printStudents(students);

            // query student : lastname - Ali
            students = session
                    .createQuery("from Student s where s.lastName = 'Ali'")
                    .getResultList();
            System.out.println("Student with last name as Ali");
            printStudents(students);

            // Student with last name as kohli or first name as altamash
            students = session
                    .createQuery("from Student s where s.lastName = 'Kohli' OR s.firstName = 'Altamash'")
                    .getResultList();
            System.out.println("Student with last name as kohli or first name as altamash");
            printStudents(students);
            
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void printStudents(List<Student> students) {
        students.stream().forEach(System.out::println);
    }
}
