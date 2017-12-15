package com.scp.hibernateassignment;

import java.util.List;

import org.hibernate.Session;

import com.scp.hibernateassignment.StudConstant.Constants;

public interface StudInterface {
	
	public boolean addStudent(Student student) throws MyException; 
	public boolean updateStudent(Student student) throws MyException;
	public boolean deleteStudent(int sId) throws MyException;
	public Student getStudent(int sId) throws MyException;
	public List<Student> getListOfStudent(Session session) throws MyException;
	public List<Student> searchStudentWithSomeCriteria(Student student,Constants param) throws MyException;
}
