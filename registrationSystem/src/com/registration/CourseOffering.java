package com.registration;

import java.util.ArrayList;

public class CourseOffering {
    private ArrayList<Registration> registrations = new ArrayList<>();

    private Integer section;
    private String instructor;


    public CourseOffering(String instructor, Integer section) {
        this.instructor = instructor;
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
    }


    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public Integer getSection() {
        return section;
    }

    public String getInstructor() {
        return instructor;
    }

    public boolean classWillRun(){
        int minStudents = 8;
        return (registrations.size() >= minStudents);
    }

    @Override
    public String toString(){
        return getInstructor() + getSection();
    }
}
