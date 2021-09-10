package com.registration;

import java.util.ArrayList;
import java.util.HashMap;


public class Student {
    private Name studentName;
    private final int sid;
    private ArrayList<Registration> registrations;

    public Student(Name studentName, int sid){
        registrations = new ArrayList<>();
        this.studentName = studentName;
//         assume unique - implemted in DB
        this.sid = sid;
    }

//     offering sink can be removed - it would be a part of the catalog
    public void register(CourseCatalogue catalogue, String courseName, String courseNumber,  int sectionNumber){
        Course newCourse = catalogue.searchCatalouge(courseName, courseNumber);

        if (newCourse != null && registrationValid()) {

            CourseOffering newOffering = newCourse.getOfferings().get(sectionNumber);
            Registration registration = new Registration(this, newOffering);
        }
        else System.out.println("This course does not exist");
    }

    public void addRegistration(Registration registration){
        registrations.add(registration);
    }


    public boolean registrationValid(){
        int maxEnrolled = 6;
        return (registrations == null || registrations.size() <= maxEnrolled);
    }

}
