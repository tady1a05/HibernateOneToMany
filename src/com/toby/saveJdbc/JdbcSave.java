package com.toby.saveJdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.toby.model.Course;
import com.toby.model.Instructor;
import com.toby.model.InstructorDetail;

public class JdbcSave {

	public static void main(String args[]) {
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
			Course course = new Course("Chinese");
			Instructor instructor = session.get(Instructor.class, "2");
			course.setInstructor(instructor);
			session.save(course);
			session.getTransaction().commit();
			session.close();

		}finally {
			factory.close();
		}
	}

}
