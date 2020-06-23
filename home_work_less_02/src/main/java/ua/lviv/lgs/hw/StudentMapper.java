package ua.lviv.lgs.hw;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper {

	public static Student map(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");

		return new Student(id, firstName, lastName);
	}
}
