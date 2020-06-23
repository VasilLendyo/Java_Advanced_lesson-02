package ua.lviv.lgs.hw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

	private static String READ_ALL = "select * from student";
	private static String CREATE = "insert into student(first_name, last_name) values(?,?)";
	private static String READ_BY_ID = "select * from student where id = ?";
	private static String UPDATE_BY_ID = "update student set first_name = ?, last_name = ? where id = ?";
	private static String DELETE_BY_ID = "delete from student where id = ?";

	private Connection connection;
	private PreparedStatement preparedStatement;

	public StudentDao(Connection connection) {
		this.connection = connection;
	}

	public void insert(Student student) throws SQLException {
		preparedStatement = connection.prepareStatement(CREATE);
		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.executeUpdate();
	}

	public Student read(int id) throws SQLException {
		preparedStatement = connection.prepareStatement(READ_BY_ID);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();

		return StudentMapper.map(resultSet);
	}

	public void update(Student student) throws SQLException {
		preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.setInt(3, student.getId());
		preparedStatement.executeUpdate();
	}

	public void delete(int id) throws SQLException {
		preparedStatement = connection.prepareStatement(DELETE_BY_ID);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
	}

	public List<Student> readAll() throws SQLException {
		List<Student> listOfStudent = new ArrayList<>();
		preparedStatement = connection.prepareStatement(READ_ALL);
		ResultSet result = preparedStatement.executeQuery();

		while (result.next()) {
			listOfStudent.add(StudentMapper.map(result));
		}
		return listOfStudent;
	}
}
