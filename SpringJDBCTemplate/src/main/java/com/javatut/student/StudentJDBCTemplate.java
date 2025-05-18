package com.javatut.student;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

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

	@Override
	public Student getStudentByStorProc(Integer id) {
		
		/*
		 create procedure getRecord
			@in_id int,
			@out_name varchar(50) output,
			@out_age int output
			As
			Begin
				Select @out_name=Name, @out_age=Age from Student where ID = @in_id
			End
			Go
			
			--Execute Stored proc
			Declare @out_name varchar(50), @out_age int
			EXEC dbo.getRecord @in_id=1,@out_name=@out_name output,@out_age=@out_age output
			select @out_name, @out_age
			
		 * */
		 
		
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getRecord");
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id)
								.addValue("out_name", Types.VARCHAR)
								.addValue("out_age", Types.INTEGER);
		Map<String, Object> out = jdbcCall.execute(in);
		
		Student student = new Student();
		student.setId(id);
		student.setName((String) out.get("out_name"));
		student.setAge((Integer) out.get("out_age"));
		return student;
	}

}
