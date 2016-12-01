package com.pechonick.registration.dao;

import com.pechonick.registration.dao.impl.StudentDao;
import com.pechonick.registration.dao.impl.TeacherDao;
import com.pechonick.registration.dao.impl.UserDao;
import com.pechonick.registration.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    private final Map<DaoType, IDao> dao = new HashMap<>();

    private DaoFactory()  {

        dao.put(DaoType.COURSE, new com.pechonick.registration.dao.impl.CourseDao());
        dao.put(DaoType.STUDENT, new StudentDao());
        dao.put(DaoType.TEACHER, new TeacherDao());
        dao.put(DaoType.USER, new UserDao());
        dao.put(DaoType.DEFAULT, new DefaultDao());
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public IDao getDao(DaoType type) {
        return dao.get(type);
    }
}
