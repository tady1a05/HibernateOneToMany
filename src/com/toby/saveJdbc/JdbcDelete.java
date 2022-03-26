package com.toby.saveJdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.toby.model.Course;
import com.toby.model.Instructor;
import com.toby.model.InstructorDetail;

public class JdbcDelete {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = null;
		
		try {
		
		/*session = factory.getCurrentSession();
			session.beginTransaction();
			Course course = session.get(Course.class, "1");
			session.delete(course);
			session.getTransaction().commit();
			session.close();
			*/
			session = factory.getCurrentSession();
			session.beginTransaction();
			Instructor instructor = session.get(Instructor.class, "2");
			for(Course course : instructor.getCourses() ) {
				course.setInstructor(null);
			}
			session.delete(instructor);
			session.getTransaction().commit();
			session.close();
		}finally {
			factory.close();
		}

	}

}
