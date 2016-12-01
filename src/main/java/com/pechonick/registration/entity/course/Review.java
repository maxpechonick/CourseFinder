package com.pechonick.registration.entity.course;

import java.io.Serializable;

public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int courseId;
    private int studentId;
    private String text;
    private int mark;

    public Review() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (getCourseId() != review.getCourseId()) return false;
        if (getStudentId() != review.getStudentId()) return false;
        if (getMark() != review.getMark()) return false;
        return getText().equals(review.getText());

    }

    @Override
    public int hashCode() {
        int result = getCourseId();
        result = 31 * result + getStudentId();
        result = 31 * result + getText().hashCode();
        result = 31 * result + getMark();
        return result;
    }

}
