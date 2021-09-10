package com.registration;

import java.util.ArrayList;

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

    public void register(CourseCatalogue catalogue, String courseName, String courseNumber,  Integer sectionNumber){
        Course theCourse = catalogue.searchCatalouge(courseName, courseNumber);

        if (theCourse != null && registrationValid()) {
            CourseOffering theOffering = theCourse.getOfferings().get(sectionNumber);
            if (!registrationDuplicate(theOffering) && theOffering.hasRoom()) {
                Registration registration = new Registration(this, theOffering);
                return;
            }
        }
        System.out.println("Cannot register");
    }

    public void addRegistration(Registration registration){
//         need some sort of check if there is a duplicate
        registrations.add(registration);
    }

    public void unRegister(CourseCatalogue catalogue, String courseName, String courseNumber,  int sectionNumber){
        Course theCourse = catalogue.searchCatalouge(courseName, courseNumber);
        CourseOffering theOffering = theCourse.getOfferings().get(sectionNumber);
        Registration matchedReg = findRegistrationMatch(theOffering);

        if (theOffering != null && matchedReg != null) matchedReg.removeRegistration(this, theOffering);
    }

    public Registration findRegistrationMatch(CourseOffering theOffering){
        ArrayList<Registration> offeringRegistrations = theOffering.getRegistrations();

        for (Registration reg: offeringRegistrations){
//             use == because we're looking for an instance match
            if (reg.getStudent() == this)
                return reg;
        }
        return null;
    }

    public void removeRegistration(Registration registration){
        for(Registration reg: registrations){
//             use == because we're looking for an instance match
            if (reg == registration) {
                registrations.remove(registration);
                return;
            }
        }
    }

    public boolean registrationDuplicate(CourseOffering theOffering){
        for (Registration reg: registrations){
//             use == because we're looking for an instance match
            if (reg.getCourseOffering() == theOffering)
                return true;
        }

        return false;
    }

    public boolean registrationValid(){
        int maxEnrolled = 6;
        return (registrations == null || registrations.size() <= maxEnrolled);
    }

    public static void main(String[] args) {
        CourseCatalogue catalogue = new CourseCatalogue();
        Course course = new Course("ENSF","607");
        CourseOffering offering = new CourseOffering("Dr.M", 3);
        course.addOffering(offering);

        Name alex = new Name("Alex", "Leakos");
        Student alexStudent = new Student(alex, 100);

        alexStudent.register(catalogue, "ENSF", "607", 3);
        alexStudent.unRegister(catalogue, "ENSF", "607", 3);
        alexStudent.register(catalogue, "ENSF", "607", 3);
        alexStudent.register(catalogue, "ENSF", "607", 3);
        alexStudent.register(catalogue, "ENSF", "607", 3);
    }

}
