package com.registration;

import java.util.HashMap;


public class Student {
    private String firstName;
    private String lastName;
    private String sid;
    private HashMap<String, Registration> registrations = new HashMap<>();

    public Student(String firstName, String lastName, String sid){
        this.firstName = firstName;
        this.lastName = lastName;
//         should be unique - handled elsewhere
        this.sid = sid;
    }

    public void register(CourseOffering offering, HashMap<String, CourseOffering> offeringSink, CourseCatalogue catalogue){
        if (inCatalogue(catalogue, offering.getCourse()) && registrationValid()) {
            try{
                Registration registration = new Registration(offering, this.sid, offeringSink);
                registrations.put(registration.getSid(), registration);
            }
            catch(Exception e) {
                System.out.println("Already registered");
                return;
            }
        }
        else System.out.println("This course does not exist");
    }

    public boolean inCatalogue(CourseCatalogue catalogue, Course course){
        return catalogue.courseExists(course);
    }

    public boolean registrationValid(){
        int maxEnrolled = 6;
        return (registrations == null || registrations.size() <= maxEnrolled);
    }
}
