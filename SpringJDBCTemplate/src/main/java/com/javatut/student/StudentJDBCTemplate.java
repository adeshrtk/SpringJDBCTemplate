package com.javatut.student;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate implements StudentDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);		
	}

	@Override
	public void create(String name, Integer age) {
		String SQL = "Insert into Student(name, age) values(? , ?)";
		jdbcTemplateObject.update(SQL, name, age);
		System.out.println("Created Record Name = " + name + " Age = " + age);
		return;
	}

	@Override
	public List<Student> listStudents() {
		String SQL = "Select * from Student";
		List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
		return students;
	}

	@Override
	public void update(Integer id, Integer age) {
		String SQL = "Update Student Set age = ? Where id = ?";
		jdbcTemplateObject.update(SQL, age, id);
		System.out.println("Updated Record with ID = " + id);
		
	}

	@Override
	public Student getStudent(Integer id) {
		String SQL = "Select * from Student Where Id = ?";
		Student student = jdbcTemplateObject.queryForObject(SQL, new Object[] {id}, new StudentMapper());
		return student;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "Delete from Student Where Id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;		
	}

}
