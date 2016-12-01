package com.pechonick.registration.entity.course;

import java.io.Serializable;
import java.sql.Date;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int teacherId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private CourseStatus status;

    public Course() {
        this.setStatus(CourseStatus.OPEN_SET);
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (getId() != course.getId()) return false;
        if (getTeacherId() != course.getTeacherId()) return false;
        if (!getName().equals(course.getName())) return false;
        if (!getStartDate().equals(course.getStartDate())) return false;
        return getEndDate().equals(course.getEndDate());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTeacherId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getStartDate().hashCode();
        result = 31 * result + getEndDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}
