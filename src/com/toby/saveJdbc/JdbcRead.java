package com.toby.saveJdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.toby.model.Course;
import com.toby.model.Instructor;
import com.toby.model.InstructorDetail;

public class JdbcRead {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = null;
		
		try {
		
			session = factory.getCurrentSession();
			session.beginTransaction();
			Instructor instructor2 = session.get(Instructor.class, "1");
			System.out.println(instructor2);
			session.getTransaction().commit();
			session.close();
		
		}finally {
			factory.close();
		}

	}

}
