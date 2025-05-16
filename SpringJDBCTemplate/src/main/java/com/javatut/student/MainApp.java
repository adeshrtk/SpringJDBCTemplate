package com.javatut.student;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
		
		// create and persist students
		System.out.println("------Records Creation----");
		studentJDBCTemplate.create("Zara", 11);
		studentJDBCTemplate.create("Nuha", 20);
		studentJDBCTemplate.create("Ayan", 15);
		
		// get list of all students from database
		System.out.println("----Listing Multiple Records-----");
		List<Student> students = studentJDBCTemplate.listStudents();
		
		for(Student record: students) {
			System.out.println("ID: " + record.getId());
			System.out.println(", Name: " + record.getName());
			System.out.println(", Age: " + record.getAge());
		}
		
		// update the student based on id
		System.out.println("----Updating Record with ID = 2");
		studentJDBCTemplate.update(3, 20);
		
		// get the student based on id and print its details
		System.out.println("---Listening Record with ID - 3---");
		Student student = studentJDBCTemplate.getStudent(3);
		System.out.println("ID: " + student.getId());
		System.out.println(", Name: " + student.getName());
		System.out.println(", Age: " + student.getAge());
		
		// delete a record based on id
		System.out.println("---Delete Record with ID = 2");
		//studentJDBCTemplate.delete(3);		
	}
}
