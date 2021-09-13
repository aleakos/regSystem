package com.registration;

import java.util.ArrayList;

public class Student {
    private final Name studentName;
    private final int sid;
    private ArrayList<Registration> registrations;
    private ArrayList<Course> coursesTaken;

    public Student(Name studentName, int sid){
        registrations = new ArrayList<>();
        coursesTaken = new ArrayList<>();

        this.studentName = studentName;
//         assume unique - implmented in DB
        this.sid = sid;
        coursesTaken.add(new Course("ENGG", "100"));
    }

    public void register(CourseCatalogue catalogue, String courseName, String courseNumber,  Integer sectionNumber){
        Course theCourse = catalogue.searchCatalouge(courseName, courseNumber);

        if (theCourse != null && belowMaxEnrolled() && meetsPreReq(theCourse)) {
            CourseOffering theOffering = theCourse.getOfferings().get(sectionNumber);
            if (!registrationDuplicate(theOffering) && theOffering.hasRoom()) {
                Registration registration = new Registration(this, theOffering);
                return;
            }
        }
        System.out.println("Cannot register for " + courseName + courseNumber);
    }

    public void addRegistration(Registration registration){
//         need some sort of check if there is a duplicate
        registrations.add(registration);
    }

    public void unRegister(CourseCatalogue catalogue, String courseName, String courseNumber,  int sectionNumber){
        Course theCourse = catalogue.searchCatalouge(courseName, courseNumber);
        if (theCourse != null){
            CourseOffering theOffering = theCourse.getOfferings().get(sectionNumber);
            Registration matchedReg = findRegistrationMatch(theOffering);
            if (theOffering != null && matchedReg != null) {
                matchedReg.removeRegistration(this, theOffering);
                return;
            }
        }
        System.out.println("Cannot un register from " + courseName + courseNumber);
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

    public void showClasses(){
        StringBuilder courses = new StringBuilder().append(studentName.getFirstName()).append("'s courses:\n");
        int i = 0;
        for (Registration reg: registrations){
            courses.append(++i).append(": ").append(reg.getCourseOffering().getCourse()).append("\n");
        }
        System.out.println(courses.toString());
    }

    public boolean belowMaxEnrolled() {
        int maxEnrolled = 6;
        return (registrations.size() < maxEnrolled);
    }

    public boolean meetsPreReq(Course newCourse){
        int preReqTally = 0;

        for (Course c: coursesTaken){
            if (newCourse.checkPreq(c)) preReqTally++;
        }
        return preReqTally == newCourse.getPreReqs().size();
    }

    public static void main(String[] args) {
        CourseCatalogue catalogue = new CourseCatalogue();
        Name alex = new Name("Alex", "Leakos");
        Student alexStudent = new Student(alex, 100);

        alexStudent.register(catalogue, "ENSF", "607", 3);
        alexStudent.register(catalogue, "ENSF", "608", 3);
        alexStudent.register(catalogue, "ENSF", "610", 3);
        alexStudent.register(catalogue, "ENSF", "614", 3);
        alexStudent.register(catalogue, "ENGG", "500", 3);
        alexStudent.register(catalogue, "ENGG", "101", 3);
        alexStudent.register(catalogue, "ENGG", "500", 3);
        alexStudent.unRegister(catalogue, "ENGG", "500", 3);
        alexStudent.register(catalogue, "ENGG", "101", 3);
        alexStudent.unRegister(catalogue, "ENGG", "101", 3);
        alexStudent.showClasses();
        alexStudent.register(catalogue, "ENSF", "700", 3);
        alexStudent.showClasses();
        alexStudent.unRegister(catalogue, "ENSF", "700", 3);
        alexStudent.unRegister(catalogue, "ENSF", "607", 3);
//         adding in a pre req
        alexStudent.coursesTaken.add(new Course("ENSF", "607"));
        alexStudent.register(catalogue, "ENSF", "700", 3);

        alexStudent.showClasses();
    }

}
