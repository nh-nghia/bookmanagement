package com.nhnghia.bookmanagement.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhnghia.bookmanagement.dbconnection.ConnectionFactory;
import com.nhnghia.bookmanagement.bo.Book;

public class BookDAO {
	public ArrayList<Book> getAllBook() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Book> bookList = new ArrayList<Book>();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM books";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				bookList.add(convertToBook(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public ArrayList<Book> searchBook(String textSearch) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Book> bookList = new ArrayList<Book>();

		try {
			// search
			String search = textSearch;
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM books WHERE name LIKE '%" + search + 
					"%' OR author LIKE '%" + search + 
					"%' OR publisher LIKE '%" + search +
					"%' OR type LIKE '%" + search +
					"%' OR language LIKE '%" + search +
					"%' OR description LIKE '%" + search + "%'";
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				bookList.add(convertToBook(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public void deleteBook(int id) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM books WHERE id = " + id;
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertBook(String name, 
							String author, 
							String publisher, 
							String type, 
							String language, 
							String description, 
							int quantity, 
							String image) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO books (name, author, publisher, type, language, description, quantity, image)" + 
							"VALUES ('" + name
							+ "', '" + author
							+ "', '" + publisher
							+ "', '" + type
							+ "', '" + language
							+ "', '" + description
							+ "'," + quantity
							+ ", '" + image
							+ "');";
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Book findBookByID (int id) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM books WHERE id = " + id;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Book book = convertToBook(resultSet);
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateBook(int id, String name, String author, String publisher, String type, String language, String description, int quantity) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE books SET name = '" + name + "', "
										+ "author = '" + author + "', "
										+ "publisher = '" + publisher + "', "
										+ "type = '" + type + "', "
										+ "language = '" + language + "', "
										+ "description = '" + description + "', "
										+ "quantity = '" + quantity + "'"
										+ "WHERE id = " + id
										+ ";";
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Book convertToBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt(1));
		book.setName(rs.getString(2));
		book.setAuthor(rs.getString(3));
		book.setPublisher(rs.getString(4));
		book.setType(rs.getString(5));
		book.setLanguage(rs.getString(6));
		book.setDescription(rs.getString(7));
		book.setQuantity(rs.getInt(8));
		book.setImage(rs.getString(9));
		return book;
	}
}
