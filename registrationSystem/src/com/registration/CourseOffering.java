package com.registration;

import java.util.ArrayList;

public class CourseOffering {
    private ArrayList<Registration> registrations = new ArrayList<>();

//    unsure if this should be here, but it makes it easier
    private Course course;

    private Integer section;
    private String instructor;
    private int maxStudents = 100;


    public CourseOffering(String instructor, Integer section) {
        this.instructor = instructor;
        this.section = section;
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
    }

    public void removeRegistration(Registration registration){
        for(Registration reg: registrations){
            if (reg.equals(registration)) {
                registrations.remove(registration);
                return;
            }
        }
    }


    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public Course getCourse(){
        return course;
    }

    public Integer getSection() {
        return section;
    }

    public String getInstructor() {
        return instructor;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public boolean classWillRun(){
        int minStudents = 8;
        return (registrations.size() >= minStudents);
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getCourse()).append(": ");
        builder.append("Section: ").append(getSection()).append(" ");
        builder.append("Instructor: ").append(getInstructor()).append("\n");

        return builder.toString();
    }

    public boolean hasRoom(){
        return (registrations.size() <= maxStudents);
    }


    public String displayOffering(){
        StringBuilder builder = new StringBuilder();

        builder.append("Course: ").append(getCourse()).append("\n");
        builder.append("Section: ").append(getSection()).append("\n");
        builder.append("Instructor: ").append(getInstructor()).append("\n");
        builder.append("Students Registered: ").append(getRegistrations().size()).append("\n");
        builder.append("Max Capacity: ").append(getMaxStudents());

        return builder.toString();
    }
}
