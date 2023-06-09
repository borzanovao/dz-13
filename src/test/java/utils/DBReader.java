package utils;

import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    private final static String URL = "jdbc:postgresql://localhost:4567/postgresDB";
    private final static String USERNAME = "borzanova";
    private final static String PASSWORD = "12345";
    private final static String QUERY_SELECT = "select * from students where id =?";
    private final static String QUERY_INSERT = "insert into students values(?,?,?,?,?)";
    private final static String QUERY_UPDATE = "update students set name=? where id=?";
    private final static String QUERY_DELETE = "delete from students where id =?";
    private final static String COL_NAME = "name";
    private final static String COL_SURNAME = "surname";
    private final static String COL_FACULTY = "faculty";
    private final static String COL_BIRTH = "birthday";

    public static List<Student> getDataFromDB(int id) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student(resultSet.getString(COL_NAME), resultSet.getString(COL_SURNAME), resultSet.getString(COL_FACULTY), resultSet.getDate(COL_BIRTH));
                students.add(student);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Can't get data from database, %s)", exception.getMessage()));
        }
        return students;
    }

    public static void insertDataToDB(int id, String name, String surname, String faculty, String birthday) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, faculty);
            preparedStatement.setDate(5, Date.valueOf(birthday));
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Can't insert data to database, %s)", exception.getMessage()));
        }
    }

    public static void updateDataIntoDB(String name, int id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Can't update data into database, %s)", exception.getMessage()));
        }
    }

    public static void deleteDataFromDB(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Can't delete data from database, %s)", exception.getMessage()));
        }
    }

    public static void main(String[] args) {
        List<Student> students = getDataFromDB(3);
        insertDataToDB(6, "Alexa", "Manul", "law", "2002-12-08");
        updateDataIntoDB("Alexandr", 6);
        deleteDataFromDB(6);
    }
}
