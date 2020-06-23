package ua.lviv.lgs.hw;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		StudentDao studentDao = new StudentDao(ConnectionUtils.openConnection());

		studentDao.readAll().forEach(System.out::println);
		System.out.println("----------------------------------------------------");

		List<Student> listOfStudent = new ArrayList<>();
		listOfStudent.add(new Student("Igor", "Lavka"));

		// CREATE
		listOfStudent.forEach(student -> {
			try {
				studentDao.insert(student);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		studentDao.readAll().forEach(System.out::println);
		System.out.println("----------------------------------------------------");

		// READ
		Student studentFromBD = studentDao.read(1);
		System.out.println(studentFromBD);

		// UPDATE
		studentFromBD.setLastName(studentFromBD.getLastName() + "-or");
		studentDao.update(studentFromBD);

		studentDao.readAll().forEach(System.out::println);
		System.out.println("----------------------------------------------------");

		// DELETE
		studentDao.delete(3);

		studentDao.readAll().forEach(System.out::println);
		System.out.println("----------------------------------------------------");
	}
}
