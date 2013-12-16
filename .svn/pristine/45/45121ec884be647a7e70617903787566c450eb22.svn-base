package com.noteofcourse.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

public class Insertion {

	static final String DB_URL = "jdbc:mysql://engr-cpanel-mysql.engr.illinois.edu";
	static final String USER = "notesofcourse_in";
	static final String PWD = "3e4r5t6y";

	Connection conn = null;
	Statement stmt = null;

	public Insertion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PWD);
			stmt = conn.createStatement();
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Insert one slides to the database CourseNotes table
	 */
	public int insertNotes(String notes, String name, String url) throws SQLException {
		int key = -1;
		String sql;
		sql = "INSERT INTO `notesofcourse_nocdb`.`TestNotes` (`NoteID`, `CourseID`, `Title`, `Link`, `Content`) VALUES (NULL, '6', '"
				+ name + "',  '" + url + "', '" + notes + "');";
		System.out.println(sql);
		stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
			key = rs.getInt(1);
		}
		rs.close();

		return key;
	}
	
	/*
	 * Insert into NoteTag table
	 */
	public void insertNoteTag(int NoteID, int TagID) throws SQLException{
		String sql = "INSERT INTO  `notesofcourse_nocdb`.`TestNoteTag` ( `ID` , `TagID` , `NoteID` ) VALUES ( NULL ,  '" +TagID+ "',  '" +NoteID+ "' )";
		stmt.executeUpdate(sql);
		return;
	}

	/*
	 * Insert all tags to Tags table
	 */
	public void insertTags(TreeSet<String> tags) throws SQLException {
		while (!tags.isEmpty()) {
			String sql;
			String tag = tags.first();
			tags.remove(tag);
			System.out.println(tag);
			sql = "INSERT INTO `notesofcourse_nocdb`.`Tags` (`TagID`, `TagName`) VALUES (NULL, '"
					+ tag + "');";
			stmt.executeUpdate(sql);
		}
		return;
	}

	public static void insertCourse(String course) {

	}

	public void close() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se) {
		}// nothing we can do
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}// end finally try
	}
}
