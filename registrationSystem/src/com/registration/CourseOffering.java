package com.registration;

import java.util.HashMap;
import java.util.Set;

public class CourseOffering {
    private final Course course;
    private HashMap<String, Registration> registrations = new HashMap<String, Registration>();
    private final Term term;
    private String instructor;


    public CourseOffering(Course course, Term term) {
        this.course = course;
        this.term = term;
    }

    public void addRegistration(Registration registration) {
        registrations.put(registration.getSid(), registration);
    }

    public Course getCourse() {
        return course;
    }

    public HashMap<String, Registration> getRegistrations() {
        return registrations;
    }

    public Term getTerm() {
        return term;
    }

    public boolean classWillRun(){
        int minStudents = 8;
        return (registrations.size() >= minStudents);
    }

    @Override
    public String toString(){
        return course + getTerm().getTerm() + getTerm().getYear();
    }
}
