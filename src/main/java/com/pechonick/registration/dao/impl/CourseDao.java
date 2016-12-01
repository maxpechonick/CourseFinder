package com.pechonick.registration.dao.impl;

import com.pechonick.registration.pool.ConnectionPool;
import com.pechonick.registration.dao.exception.DaoException;
import com.pechonick.registration.entity.course.Course;
import com.pechonick.registration.entity.course.CourseStatus;
import com.pechonick.registration.entity.course.Review;
import com.pechonick.registration.pool.exception.ConnectionPoolException;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends DefaultDao {

    private static final String GET_COURSES_QUERY = "SELECT crs_id, t_id, crs_name, crs_start_date, crs_end_date, crs_description, crs_status FROM courses LIMIT ";
    private static final String GET_COURSES_BY_STUDENT_ID = "SELECT courses.crs_id, t_id, crs_name, crs_start_date, crs_end_date, crs_description, crs_status FROM st_m2m_crs " +
            "JOIN courses on st_m2m_crs.crs_id = courses.crs_id WHERE st_id = ? LIMIT ";
    private static final String GET_COURSES_BY_TEACHER_ID = "SELECT courses.crs_id, t_id, crs_name, crs_start_date, crs_end_date, crs_description, crs_status FROM courses " +
            "WHERE t_id = ? LIMIT ";
    private static final String UPDATE_COURSE_QUERY = "UPDATE courses SET t_id = ?, crs_name = ?, crs_start_date = ?, crs_end_date = ?, " +
            "crs_description = ?, crs_status = ? WHERE crs_id = ?";
    private static final String GET_COURSE_QUERY = "SELECT * FROM courses WHERE crs_id = ?";
    private static final String DELETE_COURSE_QUERY = "DELETE FROM courses WHERE crs_id = ?";
    private static final String CREATE_COURSE_QUERY = "INSERT INTO courses VALUES(NULL,?,?,?,?,?,?)";
    private static final String APPLY_STUDENT_FOR_THE_COURSE_QUERY = "INSERT INTO st_m2m_crs VALUES(?,?)";
    private static final String CHECK_IF_SIGNED_QUERY = "SELECT * FROM st_m2m_crs WHERE st_id=? AND crs_id=?";
    private static final String APPOINT_TEACHER_FOR_THE_COURSE_QUERY = "UPDATE courses SET t_id = ? WHERE crs_id = ?";
    private static final String SET_COURSE_STATUS_QUERY = "UPDATE courses SET crs_status = ? WHERE crs_id = ?";
    private static final String ADD_REVIEW_QUERY = "UPDATE st_m2m_crs SET st_mark = ?, st_review = ? WHERE crs_id = ? AND st_id = ?";
    private static final String GET_REVIEW_QUERY = "SELECT * FROM st_m2m_crs WHERE crs_id = ? AND st_id = ?";

    @Override
    public List<Course> getAllCourses(int offset, int rowCount) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Course> courses = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_COURSES_QUERY + offset + ", " + rowCount);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt(1));
                course.setTeacherId(resultSet.getInt(2));
                course.setName(resultSet.getString(3));
                course.setStartDate(resultSet.getDate(4));
                course.setEndDate(resultSet.getDate(5));
                course.setDescription(resultSet.getString(6));
                course.setStatus(CourseStatus.valueOf(resultSet.getString(7)));

                courses.add(course);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }

        return courses;
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId, int offset, int rowCount) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Course> courses = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_COURSES_BY_STUDENT_ID + offset + ", " + rowCount);

            preparedStatement.setInt(1, studentId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt(1));
                course.setTeacherId(resultSet.getInt(2));
                course.setName(resultSet.getString(3));
                course.setStartDate(resultSet.getDate(4));
                course.setEndDate(resultSet.getDate(5));
                course.setDescription(resultSet.getString(6));
                course.setStatus(CourseStatus.valueOf(resultSet.getString(7)));

                courses.add(course);
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

        return courses;
    }

    @Override
    public void updateCourse(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COURSE_QUERY);

            preparedStatement.setInt(1, course.getTeacherId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setDate(3, course.getStartDate());
            preparedStatement.setDate(4, course.getEndDate());
            preparedStatement.setString(5, course.getDescription());
            preparedStatement.setString(6, course.getStatus().name());
            preparedStatement.setInt(7, course.getId());

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
    public Course getCourse(int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course course = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_COURSE_QUERY);

            preparedStatement.setInt(1, courseId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getInt(1));
                course.setTeacherId(resultSet.getInt(2));
                course.setName(resultSet.getString(3));
                course.setStartDate(resultSet.getDate(4));
                course.setEndDate(resultSet.getDate(5));
                course.setDescription(resultSet.getString(6));
                course.setStatus(CourseStatus.valueOf(resultSet.getString(7)));

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

        return course;
    }

    @Override
    public void deleteCourse(int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_COURSE_QUERY);

            preparedStatement.setInt(1, courseId);

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
    public Course createCourse(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_COURSE_QUERY, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, course.getTeacherId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setDate(3, course.getStartDate());
            preparedStatement.setDate(4, course.getEndDate());
            preparedStatement.setString(5, course.getDescription());
            preparedStatement.setString(6, course.getStatus().name());

            preparedStatement.executeUpdate();

            int courseId = -1;
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                courseId = rs.getInt(1);
            }

            course.setId(courseId);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }

        return course;
    }

    @Override
    public void applyStudentForTheCourse(int studentId, int courseId) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(APPLY_STUDENT_FOR_THE_COURSE_QUERY);

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);

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
    public boolean checkStudentIfApplied(int studentId, int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CHECK_IF_SIGNED_QUERY);

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
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

        return false;
    }

    @Override
    public List<Course> getCoursesByTeacherId(int teacherId, int offset, int rowCount) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Course> courses = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_COURSES_BY_TEACHER_ID + offset + ", " + rowCount);

            preparedStatement.setInt(1, teacherId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt(1));
                course.setTeacherId(resultSet.getInt(2));
                course.setName(resultSet.getString(3));
                course.setStartDate(resultSet.getDate(4));
                course.setEndDate(resultSet.getDate(5));
                course.setDescription(resultSet.getString(6));
                course.setStatus(CourseStatus.valueOf(resultSet.getString(7)));

                courses.add(course);
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

        return courses;
    }

    @Override
    public void appointTeacherForTheCourse(int teacherId, int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(APPOINT_TEACHER_FOR_THE_COURSE_QUERY);

            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, courseId);

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
    public void setCourseStatus(int courseId, CourseStatus status) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SET_COURSE_STATUS_QUERY);

            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, courseId);

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
    public void addReview(Review review) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ADD_REVIEW_QUERY);

            preparedStatement.setInt(1, review.getMark());
            preparedStatement.setString(2, review.getText());
            preparedStatement.setInt(3, review.getCourseId());
            preparedStatement.setInt(4, review.getStudentId());

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
    public Review getReview(int studentId, int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Review review = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_QUERY);

            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, studentId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                review = new Review();
                review.setMark(resultSet.getInt(4));
                review.setText(resultSet.getString(5));

                if (review.getText() == null && review.getMark() == 0) {
                    return null;
                }
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

        return review;
    }
}
