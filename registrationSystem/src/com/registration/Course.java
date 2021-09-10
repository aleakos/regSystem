package com.registration;

import java.util.Set;
import com.google.gson.*;

public class Course {

    private String courseName;
    private String courseNumber;
    private Set<Course> preqs;

    public Course(String courseName, String courseNumber){
        setCourseName(courseName);
        setCourseNumber(courseNumber);
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

    public void addPreq(Course course){preqs.add(course); }

    public boolean checkPreq(Course course){
        return preqs.contains(course);
    }

    @Override
    public String toString(){
        return getCourseName() + getCourseNumber();
    }
}

