package com.javatut.student;

import java.util.List;

import javax.sql.DataSource;

public interface StudentDAO {
	 
	public void setDataSource(DataSource ds);	
	
	public void create(String name, Integer age);
	
	public List<Student> listStudents();
	
	public void update(Integer id, Integer age);
	
	public Student getStudent(Integer id);
	
	public void delete(Integer id);
	
	public Student getStudentByStorProc(Integer id);
	
	public Student getStudentByFunc(Integer id);
	
	public void batchUpdate(final List<Student> students);
	
	public void objectBatchUpdate(final List<Student> students);
	
}
