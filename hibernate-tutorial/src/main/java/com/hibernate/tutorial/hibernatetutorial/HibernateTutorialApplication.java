package com.hibernate.tutorial.hibernatetutorial;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateTutorialApplication {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";

		try {
			System.out.println("Connecting to database: "+ jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("Connection successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
