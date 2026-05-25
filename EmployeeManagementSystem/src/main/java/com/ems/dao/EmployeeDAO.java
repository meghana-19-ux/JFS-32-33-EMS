package com.ems.dao;
import com.ems.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EmployeeDAO {
private Connection connection;
public boolean saveEmployee(Employee employee) throws SQLException {
	connection=ConnectionManager.getConnection();
	String query="insert into employee(name, email, password, age ,gender ,mobile ,department, address ) values(?,?,?,?,?,?,?,?)";
	PreparedStatement ps=connection.prepareStatement(query);
	ps.setString(1, employee.getName());
	ps.setString(2, employee.getEmail());
	ps.setString(3, employee.getPassword());
	ps.setInt(4, employee.getAge());
	ps.setString(5, employee.getGender());
	ps.setString(6, employee.getMobile());
	ps.setString(7, employee.getDepartment());
	ps.setString(8, employee.getAddress());
	
	int count=ps.executeUpdate();
	if(count==1) {
		return true;
	}
	else {
	return false;
}
}
public void commit() throws SQLException{
	connection.commit();
	connection.close();
}
public void rollback() throws SQLException{
	connection.rollback();
	connection.close();
}
public boolean checkLogin(String email, String password) throws SQLException {
	Connection connection=ConnectionManager.getConnection();
	String query="select count(*) from employee where email=? and password =?";
	PreparedStatement statement=connection.prepareStatement(query);
	statement.setString(1, email);
	statement.setString(2, password);
	
	ResultSet set=statement.executeQuery();
	int count=0;
	if(set.next()) {
		count=set.getInt(1);
	}
	if(count==1) {
		return true;
	}else {
		
	}
	return false;
}

}