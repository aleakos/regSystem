package com.registration;

import java.util.ArrayList;

public class Student {
    private final Name studentName;
    private static int nextSid = 100;
    private int sid;
    private CourseCatalogue catalogue;
    private ArrayList<Registration> registrations;
    private ArrayList<Course> coursesCompleted;

    public Student(Name studentName, CourseCatalogue catalogue){
        registrations = new ArrayList<>();
        coursesCompleted = new ArrayList<>();
        this.studentName = studentName;
        this.catalogue = catalogue;

        setSid();

    }

    public void register(String courseName, String courseNumber,  Integer sectionNumber){
        Course theCourse = catalogue.searchCatalouge(courseName, courseNumber);

        if (theCourse != null && belowMaxEnrolled() && meetsPreReq(theCourse)) {
            CourseOffering theOffering = theCourse.getOfferings().get(sectionNumber);
            if (theOffering != null && !registrationDuplicate(theOffering) && theOffering.hasRoom()) {
                Registration registration = new Registration(this, theOffering);
                System.out.println();
                System.out.println("Registration successful!");
                System.out.println();
                return;
            }
        }
        System.out.println();
        System.out.println("Cannot register for " + courseName + courseNumber+ " section " + sectionNumber);
        System.out.println();
    }

    public void addRegistration(Registration registration){
//         need some sort of check if there is a duplicate
        registrations.add(registration);
    }

    public void unRegister(String courseName, String courseNumber,  int sectionNumber){
        Course theCourse = catalogue.searchCatalouge(courseName, courseNumber);
        if (theCourse != null){
            CourseOffering theOffering = theCourse.getOfferings().get(sectionNumber);
            Registration matchedReg = findRegistrationMatch(theOffering);
            if (matchedReg != null) {
                matchedReg.removeRegistration(this, theOffering);
                System.out.println();
                System.out.println("Successfully un enrolled!");
                System.out.println();
                return;
            }
        }
        System.out.println();
        System.out.println("Cannot un register from " + courseName + courseNumber + " section " + sectionNumber);
        System.out.println();
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
            courses.append(++i).append(": ").append(reg.getCourseOffering()).append("\n");
        }
        System.out.println(courses.toString());
    }

    public boolean belowMaxEnrolled() {
        int maxEnrolled = 6;
        return (registrations.size() < maxEnrolled);
    }

    public boolean meetsPreReq(Course newCourse){
        int preReqTally = 0;

        for (Course c: coursesCompleted){
            if (newCourse.checkPreq(c)) preReqTally++;
        }
        return preReqTally == newCourse.getPreReqs().size();
    }

    private void setSid(){
        this.sid = nextSid;
        nextSid++;
    }

    public int getSid(){
        return sid;
    }

    public void addCoursesCompleted(Course courseTaken){
        coursesCompleted.add(courseTaken);
    }

    public Name getStudentName(){
        return studentName;
    }

    public static void main(String[] args) {
//        testing
        CourseCatalogue catalogue = new CourseCatalogue();
        Name alex = new Name("Alex", "Leakos");
        Student alexStudent = new Student(alex, catalogue);
        alexStudent.addCoursesCompleted(new Course("ENGG", "100"));

        alexStudent.register("ENSF", "607", 3);
        alexStudent.register("ENSF", "608", 3);
        alexStudent.register("ENSF", "610", 3);
        alexStudent.register("ENSF", "614", 3);
        alexStudent.register("ENGG", "500", 3);
        alexStudent.register("ENGG", "101", 3);
        alexStudent.showClasses();
        alexStudent.register("ENGG", "500", 3);
        alexStudent.unRegister("ENGG", "500", 3);
        alexStudent.showClasses();
        alexStudent.unRegister("ENGG", "101", 3);
        alexStudent.showClasses();
        alexStudent.register("ENSF", "700", 3);
        alexStudent.showClasses();
        alexStudent.unRegister("ENSF", "700", 3);
        alexStudent.unRegister("ENSF", "607", 3);
//         adding in a pre req
        alexStudent.addCoursesCompleted(new Course("ENSF", "607"));
        alexStudent.register("ENSF", "700", 3);

        alexStudent.showClasses();
    }

}
