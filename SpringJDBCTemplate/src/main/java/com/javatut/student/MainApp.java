package com.javatut.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
		
		// create and persist students
		createStudent(studentJDBCTemplate);
		
		// get list of all students from database
		getAllStudents(studentJDBCTemplate);
		
		// update the student based on id
		updateStudentById(studentJDBCTemplate);
		
		// get the student based on id and print its details
		getStudentById(studentJDBCTemplate);
		
		// delete a record based on id
		// deleteStudent(studentJDBCTemplate);	
		
		// get the student based on id and print its details
		getStudentByStorProc(studentJDBCTemplate);
		
		// update the student based on Id and print its details
		batchUpdate(studentJDBCTemplate);	
		
		// update the student based on ID and print its details
		objectBatchUpdate(studentJDBCTemplate);
		
	}
	
	public static void createStudent(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("------Records Creation----");
		studentJDBCTemplate.create("Zara", 11);
		studentJDBCTemplate.create("Nuha", 20);
		studentJDBCTemplate.create("Ayan", 15);		
	}
	
	public static void getAllStudents(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("----Listing Multiple Records-----");
		List<Student> students = studentJDBCTemplate.listStudents();
		
		for(Student record: students) {
			System.out.println("ID: " + record.getId());
			System.out.println(", Name: " + record.getName());
			System.out.println(", Age: " + record.getAge());
		}
	}
	
	public static void deleteStudent(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("---Delete Record with ID = 2");
		studentJDBCTemplate.delete(3);
	}
	
	public static void getStudentByStorProc(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("---Listening Record with ID - 1 with Stored Proc---");
		Student student1 = studentJDBCTemplate.getStudentByStorProc(1);
		System.out.println("ID: " + student1.getId());
		System.out.println(", Name: " + student1.getName());
		System.out.println(", Age: " + student1.getAge());
	}
	
	public static void getStudentById(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("---Listening Record with ID - 3---");
		Student student = studentJDBCTemplate.getStudent(3);
		System.out.println("ID: " + student.getId());
		System.out.println(", Name: " + student.getName());
		System.out.println(", Age: " + student.getAge());
	}
	
	public static void updateStudentById(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("----Updating Record with ID = 2");
		studentJDBCTemplate.update(3, 20);		
	}
	
	public static void batchUpdate(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("---Listening Record with ID - 1 with Function");
		Student student2 = studentJDBCTemplate.getStudentByFunc(1);
		System.out.println("ID: " + student2.getId());
		System.out.println(", Name: " + student2.getName());
		
		// batch update to students
		Student updStudent1 = new Student();
		updStudent1.setId(1);
		updStudent1.setAge(15);
		
		
		Student updStudent2 = new Student();
		updStudent2.setId(3);
		updStudent2.setAge(25);
		
		List<Student> studentsList = new ArrayList<Student>();
		studentsList.add(updStudent1);
		studentsList.add(updStudent2);
		
		studentJDBCTemplate.batchUpdate(studentsList);
		System.out.println("Updated Student");
	}
	
	public static void objectBatchUpdate(StudentJDBCTemplate studentJDBCTemplate) {
		System.out.println("---Listening Record with ID - 1 with Function");
		Student student2 = studentJDBCTemplate.getStudentByFunc(1);
		System.out.println("ID: " + student2.getId());
		System.out.println(", Name: " + student2.getName());
		
		// batch update to students
		Student updStudent1 = new Student();
		updStudent1.setId(4);
		updStudent1.setAge(15);
		
		
		Student updStudent2 = new Student();
		updStudent2.setId(5);
		updStudent2.setAge(25);
		
		List<Student> studentsList = new ArrayList<Student>();
		studentsList.add(updStudent1);
		studentsList.add(updStudent2);
		
		studentJDBCTemplate.objectBatchUpdate(studentsList);
		System.out.println("Updated Student");
	}
	
	
}
