package com.nhnghia.bookmanagement.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhnghia.bookmanagement.bo.Contact;
import com.nhnghia.bookmanagement.dbconnection.ConnectionFactory;

public class ContactDAO {
	public void insertContact (String firstName, String lastName, String email, String message) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO contact (first_name, last_name, email, message)" + 
					"VALUES ('" + firstName
					+ "', '" + lastName
					+ "', '" + email
					+ "', '" + message
					+ "');";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contact> getAllContact (){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM contact";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				contactList.add(convertToContact(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	
	private static Contact convertToContact(ResultSet rs) throws SQLException {
		Contact contact = new Contact();
		contact.setFirstName(rs.getString(1));
		contact.setLastName(rs.getString(2));
		contact.setEmail(rs.getString(3));
		contact.setMessage(rs.getString(4));
		return contact;
	}
}
