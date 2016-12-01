package com.pechonick.registration.dao.impl;

import com.pechonick.registration.pool.ConnectionPool;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.student.Student;
import com.pechonick.registration.entity.student.StudentStatus;
import com.pechonick.registration.pool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDao extends DefaultDao {

    private static final String GET_STUDENTS_BY_COURSE_ID = "SELECT u_id, ci_firstname, ci_secondname, ci_thirdname, ci_email, ci_phone " +
            "FROM common_info JOIN st_m2m_crs on st_m2m_crs.st_id = common_info.u_id WHERE st_m2m_crs.crs_id = ?";
    private static final String GET_STUDENT_QUERY = "SELECT * FROM common_info JOIN students ON common_info.u_id = students.st_id " +
            "WHERE students.st_id = ?";
    private static final String DELETE_STUDENT_QUERY = "DELETE FROM users WHERE u_id = ?";
    private static final String UPDATE_STUDENT_QUERY = "UPDATE students SET st_course=?,st_exp_date=? WHERE st_id = ?";
    private static final String CREATE_STUDENT_QUERY = "INSERT INTO students VALUES(NULL,?,?); INSERT INTO common_info VALUES(ci_birth)";
    private static final String SET_STUDENT_STATUS_QUERY = "UPDATE st_m2m_crs SET st_status=? WHERE st_id = ? AND crs_id = ?";

    @Override
    public List<Student> getStudentsByCourseId(int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_STUDENTS_BY_COURSE_ID);

            preparedStatement.setInt(1, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(1));
                student.setFirstname(resultSet.getString(2));
                student.setSecondname(resultSet.getString(3));
                student.setThirdname(resultSet.getString(4));
                student.setEmail(resultSet.getString(5));
                student.setPhone(resultSet.getString(6));
                students.add(student);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }

        return students;
    }

    @Override
    public Student getStudent(int studentId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_STUDENT_QUERY);

            preparedStatement.setInt(1, studentId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt(1));
                student.setFirstname(resultSet.getString(2));
                student.setSecondname(resultSet.getString(3));
                student.setThirdname(resultSet.getString(4));
                student.setUniversity(resultSet.getString(5));
                student.setFaculty(resultSet.getString(6));
                student.setDepartment(resultSet.getString(7));
                student.setBirthDate(resultSet.getDate(8));
                student.setEmail(resultSet.getString(9));
                student.setPhone(resultSet.getString(10));
                student.setCourse(resultSet.getInt(12));
                student.setExpDate(resultSet.getInt(13));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }

        return student;
    }

    @Override
    public void deleteStudent(int studentId) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_STUDENT_QUERY);

            preparedStatement.setInt(1, studentId);

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void updateStudent(Student student) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STUDENT_QUERY);

            preparedStatement.setInt(1, student.getCourse());
            preparedStatement.setInt(2, student.getExpDate());
            preparedStatement.setInt(3, student.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void createStudent(Student student) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_STUDENT_QUERY);

            preparedStatement.setInt(1, student.getCourse());
            preparedStatement.setInt(2, student.getExpDate());

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void setStudentStatus(StudentStatus status, int studentId, int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SET_STUDENT_STATUS_QUERY);

            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, courseId);

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }
}

