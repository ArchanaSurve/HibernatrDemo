package com.scp.hibernateassignment;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;


import com.scp.hibernateassignment.StudConstant.Constants;








public class StudentOperations implements StudInterface{
	
	
	
	private void checkForNullFields(Student student) throws MyException {

		if (null == student ||  student.getsName().trim().length()<=1 ||
			 student.getsAddr().trim().length()<=1 ||
		     student.getsAge() <=0) {
			throw new MyException("Book object OR it's fields cann't be null");
		}
	}


	public boolean addStudent(Student student) throws MyException {
		checkForNullFields(student);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.save(student);
		}
		catch (Exception e) 
		{
				transaction.rollback();
				throw new MyException("Error in Adding Student Info");
		}
		finally 
		{
		     resourceCleanupActivities(session,transaction);
		}
		return true;
	}

	private void resourceCleanupActivities(Session session, Transaction transaction) 
	{
		
		if(null!=transaction)
			transaction.commit();
		if(null!=session)
			session.close();
	}
	
	
	public Student getStudent(int sId) throws MyException {
		
		if(sId<=0) {
			throw new MyException("Student id can't be zero");
		}

		Student student=null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			student=session.get(Student.class,sId);
		} catch (Exception e) {
			transaction.rollback();
			throw new MyException("Error in getting Student info");
		}
		System.out.println(student);
		return student;
	}

	public boolean updateStudent(Student student)throws MyException {
		checkForNullFields(student);
		if(null==getStudent(student.getsId())) {
			throw new MyException("Given Student doesnt exist so can't update..!");
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Student st=null;
		try
		{
			st=(Student)session.get(Student.class,student.getsId());
			st.setsName("Ajit");
			
			 
			session.update(st);
			
		} catch (Exception e) 
		{
			transaction.rollback();
			throw new MyException("Error while updating student info");
		}finally {
			resourceCleanupActivities(session,transaction);
		}
		
		
		return true;
	}

	public boolean deleteStudent(int sId) throws MyException {
		if(sId<=0 ||null==getStudent(sId)) {
			throw new MyException("Student id can't be zero or doesn't exist in db");
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trac = session.beginTransaction();
		try {
			session.delete(getStudent(sId));
		} catch (Exception e) {
			trac.rollback();
			throw new MyException("Error in deleting book");
		}finally {
			resourceCleanupActivities(session,trac);
		}
		return false;
	}

	

	public List<Student> getListOfStudent(Session session)throws MyException {

		List listOfStudents =null;
		try {
			 listOfStudents = session.createQuery("FROM Employee").list(); 
	         for (Iterator iterator = listOfStudents.iterator(); iterator.hasNext();){
	            Student student = (Student) iterator.next(); 
	            System.out.print("Student id: " + student.getsId()); 
	            System.out.print("  Student name: " + student.getsName()); 
	            System.out.println("  age: " + student.getsAge()); 
	            System.out.println("  Student address: " + student.getsAddr()); 
	            
	         }
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new MyException("Error while getting Student list");
		}
		return listOfStudents;
		
	}

	public List<Student> searchStudentWithSomeCriteria(Student student,Constants param) throws MyException {
		checkForNullFields(student);
		List<Student> students = new ArrayList<Student>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trac = session.beginTransaction();
		try {
			Criteria cr = session.createCriteria(Student.class);
			switch(param) {
				case STUDID:
					students.add(session.get(Student.class,student.getsId()));
					break;
				case STUDNAME:
					cr.add(Restrictions.eq("SName",student.getsName()));
					students=cr.list();
					break;
				case STUDAGE:
					cr.add(Restrictions.eq("sAge",student.getsAge()));
					students=cr.list();
					break;
				case STUDADDR:
					cr.add(Restrictions.eq("sAddr",student.getsAddr()));
					students= cr.list();
					break;
				case ALL:
					students=getListOfStudent(session);
					break;
				default :
					throw new MyException("Invalid search criteria..!");
			}
							
		} catch (Exception e) {
			//trac.rollback();
			throw new MyException("Error while getting student with criteria from db");
		} finally {
			resourceCleanupActivities(session, trac);
		}
		return (List<Student>) students;
		
	}

}
