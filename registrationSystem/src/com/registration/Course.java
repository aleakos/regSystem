package com.registration;

import java.util.HashMap;

public class Course {

    private String courseName;
    private String courseNumber;
    private HashMap<String, Course> preReqs;
    private HashMap<Integer, CourseOffering> offerings;

    public Course(String courseName, String courseNumber){
        setCourseName(courseName);
        setCourseNumber(courseNumber);
        offerings = new HashMap<>();
        preReqs = new HashMap<>();
    }

    public void addPreReq(Course course){preReqs.put(course.toString(), course); }

    public boolean checkPreReq(Course course){
        return (preReqs.containsKey(course.toString()) || preReqs.size() == 0);
    }

    public void addOffering(CourseOffering offering){
        offerings.put(offering.getSection(), offering);
        offering.setCourse(this);
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public HashMap<Integer, CourseOffering> getOfferings(){
        return offerings;
    }

    public HashMap<String, Course> getPreReqs() {
        return preReqs;
    }

    @Override
    public String toString(){
        return getCourseName() + getCourseNumber();
    }

    public void showAllOfferings(){
        for (CourseOffering o: offerings.values()){
            System.out.println(o.displayOffering());
            System.out.println();
        }
    }

    public boolean equals(Course course){
        return (course.getCourseName().equals(this.getCourseName()) &&
                course.getCourseNumber().equals(this.getCourseNumber()));
    }


    public static void main(String[] args) {
        Course course = new Course("ENSF","607");
        CourseOffering offering = new CourseOffering("Dr.M", 3);
        course.addOffering(offering);
        System.out.println(offering.displayOffering());
    }
}

