package com.pechonick.registration.entity.teacher;

import com.pechonick.registration.entity.common.User;

public class Teacher extends User {

    private int experience;
    private TeacherPosition position;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public TeacherPosition getPosition() {
        return position;
    }

    public void setPosition(TeacherPosition position) {
        this.position = position;
    }


}
