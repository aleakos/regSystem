package com.registration;

import java.util.ArrayList;

public class CourseOffering {
    private ArrayList<Registration> registrations = new ArrayList<>();

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
//             use == because we're looking for an instance match
            if (reg == registration) {
                registrations.remove(registration);
                return;
            }
        }
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

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public boolean classWillRun(){
        int minStudents = 8;
        return (registrations.size() >= minStudents);
    }

    public boolean hasRoom(){
        return (registrations.size() <= maxStudents);
    }

    @Override
    public String toString(){
        return getSection().toString();
    }
}
