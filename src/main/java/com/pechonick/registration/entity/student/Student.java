package com.pechonick.registration.entity.student;

import com.pechonick.registration.entity.common.User;

public class Student extends User {

    public static final int MAX_APPLIED_FOR_COURSE_COUNT = 10;

    private int course;
    private int expDate;
    private Boolean isReviewed;

    public Student() {

    }

    public Boolean getReviewed() {
        return isReviewed;
    }

    public void setReviewed(Boolean reviewed) {
        isReviewed = reviewed;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getExpDate() {
        return expDate;
    }

    public void setExpDate(int expDate) {
        this.expDate = expDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (getCourse() != student.getCourse()) return false;
        return getExpDate() == student.getExpDate();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCourse();
        result = 31 * result + getExpDate();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "course=" + course +
                ", expDate=" + expDate +
                ", isReviewed=" + isReviewed +
                '}';
    }
}
