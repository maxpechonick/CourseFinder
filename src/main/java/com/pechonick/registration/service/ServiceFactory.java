package com.pechonick.registration.service;

import com.pechonick.registration.service.impl.AdminService;
import com.pechonick.registration.service.impl.StudentService;
import com.pechonick.registration.service.impl.TeacherService;
import com.pechonick.registration.service.impl.UserService;
import com.pechonick.registration.service.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final Map<ServiceType, IService> services = new HashMap<>();

    private ServiceFactory()  {

        services.put(ServiceType.ADMIN, new AdminService());
        services.put(ServiceType.COURSE, new com.pechonick.registration.service.impl.CourseService());
        services.put(ServiceType.STUDENT, new StudentService());
        services.put(ServiceType.TEACHER, new TeacherService());
        services.put(ServiceType.USER, new UserService());
        services.put(ServiceType.DEFAULT, new DefaultService());
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public IService getService(ServiceType type) {
        return services.get(type);
    }
}
