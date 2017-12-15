package com.scp.hibernateassignment;

import org.hibernate.Session;

import com.scp.hibernateassignment.StudConstant.Constants;

public class StudentMain {
	private static final Session session = null;

	public static void main(String[] args) throws MyException {
		
		StudInterface studint=new StudentOperations();
		Student st1=new Student(1,"Sweety",18,"pune");
		Student st2=new Student(2,"Amisha",16,"Shivapur");
		Student st3=new Student(3,"Asha",20,"US");
		Student st4=new Student(4,"pooja",16,"US");
		/*studint.addStudent(st1);
		studint.addStudent(st2);
		studint.addStudent(st3);
		
		studint.getStudent(2);
		//studint.deleteStudent(2);
		studint.updateStudent(st1);*/
		//studint.addStudent(st4);
	    System.out.println(studint.searchStudentWithSomeCriteria(st2,StudConstant.Constants.STUDAGE ));
		
	}

}
